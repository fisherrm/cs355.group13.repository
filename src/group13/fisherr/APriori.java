package group13.fisherr;



import java.util.ArrayList;

public class APriori {
	
 public static void main(String[] args) {
	
	 //Add items to inventory
	 Item a = new Item("a");
	 Item b = new Item("b");
	 Item c = new Item("c");
	 Item d = new Item("d");
	 Item e = new Item("e");
	 ArrayList<Item> inventory = new ArrayList<Item>();
	 inventory.add(a);
	 inventory.add(b);
	 inventory.add(c);
	 inventory.add(d);
	 inventory.add(e);
	 
	//1. Create items for ItemSets
	ArrayList<Item> items1 = new ArrayList<Item>(); 
	items1.add(new Item("A"));
	items1.add(new Item("B")); 
	items1.add(new Item("E"));
	ArrayList<Item> items2 = new ArrayList<Item>(); 
	items2.add(new Item("B"));
	items2.add(new Item("D")); 
	ArrayList<Item> items3 = new ArrayList<Item>(); 
	items3.add(new Item("B"));
	items3.add(new Item("C"));
	ArrayList<Item> items4 = new ArrayList<Item>(); 
	items4.add(new Item("A"));
	items4.add(new Item("B"));
	items4.add(new Item("D")); 
	ArrayList<Item> items5 = new ArrayList<Item>(); 
	items5.add(new Item("A"));
	items5.add(new Item("C"));
	ArrayList<Item> items6 = new ArrayList<Item>(); 
	items6.add(new Item("B"));
	items6.add(new Item("C"));
	ArrayList<Item> items7 = new ArrayList<Item>(); 
	items7.add(new Item("A"));
	items7.add(new Item("C"));
	ArrayList<Item> items8 = new ArrayList<Item>();
	items8.add(new Item("A"));
	items8.add(new Item("B"));
	items8.add(new Item("C"));
	items8.add(new Item("E"));
	ArrayList<Item> items9 = new ArrayList<Item>(); 
	items9.add(new Item("A"));
	items9.add(new Item("B"));
	items9.add(new Item("C"));
	
	
	 
	 //2. Create ItemSets
	 ItemSet itemSet1 = new ItemSet(items1);
	 ItemSet itemSet2 = new ItemSet(items2);
	 ItemSet itemSet3 = new ItemSet(items3);
	 ItemSet itemSet4 = new ItemSet(items4);
	 ItemSet itemSet5 = new ItemSet(items5);
	 ItemSet itemSet6 = new ItemSet(items6);
	 ItemSet itemSet7 = new ItemSet(items7);
	 ItemSet itemSet8 = new ItemSet(items8);
	 ItemSet itemSet9 = new ItemSet(items9);

	
	 //3. Make Transactions
	 Transaction t1 = new Transaction (itemSet1);
	 Transaction t2 = new Transaction(itemSet2);
	 Transaction t3 = new Transaction(itemSet3);
	 Transaction t4 = new Transaction(itemSet4);
	 Transaction t5 = new Transaction(itemSet5);
	 Transaction t6 = new Transaction(itemSet6);
	 Transaction t7 = new Transaction(itemSet7);
	 Transaction t8 = new Transaction(itemSet8);
	 Transaction t9 = new Transaction(itemSet9);
	 //4. Add Transactions to list
	 ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	 
	 transactions.add(t1);
	 transactions.add(t2);
	 transactions.add(t3);
	 transactions.add(t4);
	 transactions.add(t5);
	 transactions.add(t6);
	 transactions.add(t7);
	 transactions.add(t8);
	 transactions.add(t9);
	 //Create a TransactionSet from all transactions
	 TransactionSet tranSet = new TransactionSet(transactions);
	 //System.out.println(tranSet);
	 
	 
	 //Call A Priori
	 doApriori(tranSet, 2.0);
 }
	 
	 public static TransactionSet doApriori(TransactionSet tranSet, double minimumSupportLevel ){
	 
	 ItemSet uniqueItems = tranSet.getUniqueItems();
	 
	 //System.out.println("UNIQUE:" +uniqueItems);
	 
	 TransactionSet large = new TransactionSet();		//resultant large ItemSets
	 TransactionSet iterations = new TransactionSet();	//large ItemSet in each iteration
	 TransactionSet candidates = new TransactionSet();	//candidate ItemSet in each iteration
	 
	//Part 1: Generate all candidate single-item sets
	//first iteration (1-item ItemSets)
	for(int i = 0; i < uniqueItems.getItems().size();i++){
		Item candidate = uniqueItems.getItems().get(i);
		ItemSet itemSet = new ItemSet();
		itemSet.add(candidate);
		//System.out.println("ITEMSET: " + itemSet);
		candidates.add(new Transaction(itemSet));
		//or do I add only one transaction?
		
	}
	//System.out.println("Candidate 1: " + candidates);
	
	//next iterations
	int k = 2;
	while(candidates.getTransactionSet().size()!=0){
		//System.out.println("while loop start: "  +candidates.getTransactionSet().size());
		//set iterations from candidates (pruning)
		iterations.getTransactionSet().clear();
		
		for(Transaction transaction :candidates.getTransactionSet()){
			double supportLevel = tranSet.findSupportLevel(transaction.getItemSet());
			transaction.getItemSet().setSupport(supportLevel);
			//System.out.println(transaction.getItemSet());
			//break;
		
			if(transaction.getItemSet().getSupport() >=minimumSupportLevel){
				iterations.add(transaction);
				if(transaction.getItemSet().getItems().size() >1){
					large.add(transaction);
					//System.out.println("large added: " + transaction);
				}
				
			}
		}
			
			
			//set candidates for next iteration (find supersets of iterations)
			candidates.getTransactionSet().clear();
			//System.out.println("ITERATIONS:" + iterations);
			candidates.setTransactionSet(findSubsets(iterations.getUniqueItems(), k));//get k-item subsets
		
			//System.out.println("NEW CANDIDATES:" + candidates);
			k+=1;
		
		
		}
		System.out.println("LARGE:" +large);
		return large;
		
		
		
		
	}
		
		
		
	
	
	
 
 
 private static ArrayList<Transaction>findSubsets(ItemSet itemSet, int k) {
	// TODO Auto-generated method stub
	 
	 ArrayList<Transaction> allSubsets  = new ArrayList<Transaction>();
	 //System.out.println(itemSet);
	 int subsetCount = (int)Math.pow(2, itemSet.getItems().size());
	 //System.out.println("SubsetCount: " + subsetCount);
	 for(int i = 0 ; i < subsetCount; i++){
		ItemSet subset = new ItemSet();
		//System.out.println("i: " + i);
		for(int bitIndex = 0; bitIndex < itemSet.getItems().size(); bitIndex++){
			if(getBit(i, bitIndex)==1){
				//System.out.println("i: " + i +", bitIndex: " + bitIndex);
				//System.out.println(itemSet);
				subset.add(itemSet.getItems().get(bitIndex));
					
				}
				
			}
		
		if(subset.getItems().size()==k-1){
			allSubsets.add(new Transaction(subset));
		}
	 }
	 
	
	return allSubsets;
}

private static int getBit(int value, int position) {
	
	// TODO Auto-generated method stub
	int bit = value & (int)Math.pow(2, position);
	if(bit>0){
		return 1;
	}else{
		return 0;
	}
	
}





}




	