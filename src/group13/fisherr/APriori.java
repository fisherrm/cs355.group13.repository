package group13.fisherr;

import java.io.File;
import java.io.IOException;
import java.security.AllPermission;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class APriori {

	public static void main(String[] args) throws IOException {

		String fileName1 = "src/transactionSet_01.txt";
		String fileName2 = "src/transactionSet_02.txt";
		String fileName3 = "src/transactionSet_03.txt";
		// String fileName =
		// "H://CS/CS 355/GROUP13/GROUP13.FISHERR/src/transactionSet_01.txt";
		// String fileName = "H://CS/CS 355/GROUP13/textFiles/test1.txt";

		/*
		  
		 //Add items to inventory Item a = new Item("a"); Item b = new
		  Item("b"); Item c = new Item("c"); Item d = new Item("d"); Item e =
		  new Item("e"); ArrayList<Item> inventory = new ArrayList<Item>();
		  inventory.add(a); inventory.add(b); inventory.add(c);
		  inventory.add(d); inventory.add(e);
		  
		 //1. Create items for ItemSets ArrayList<Item> items1 = new
		  ArrayList<Item>(); items1.add(new Item("A")); items1.add(new
		  Item("B")); items1.add(new Item("E")); ArrayList<Item> items2 = new
		  ArrayList<Item>(); items2.add(new Item("B")); items2.add(new
		  Item("D")); ArrayList<Item> items3 = new ArrayList<Item>();
		  items3.add(new Item("B")); items3.add(new Item("C")); ArrayList<Item>
		  items4 = new ArrayList<Item>(); items4.add(new Item("A"));
		  items4.add(new Item("B")); items4.add(new Item("D")); ArrayList<Item>
		  items5 = new ArrayList<Item>(); items5.add(new Item("A"));
		  items5.add(new Item("C")); ArrayList<Item> items6 = new
		  ArrayList<Item>(); items6.add(new Item("B")); items6.add(new
		  Item("C")); ArrayList<Item> items7 = new ArrayList<Item>();
		  items7.add(new Item("A")); items7.add(new Item("C")); ArrayList<Item>
		  items8 = new ArrayList<Item>(); items8.add(new Item("A"));
		  items8.add(new Item("B")); items8.add(new Item("C")); items8.add(new
		  Item("E")); ArrayList<Item> items9 = new ArrayList<Item>();
		  items9.add(new Item("A")); items9.add(new Item("B")); items9.add(new
		  Item("C"));
		  
		  
		  
		  //2. Create ItemSets ItemSet itemSet1 = new ItemSet(items1); ItemSet
		  itemSet2 = new ItemSet(items2); ItemSet itemSet3 = new
		  ItemSet(items3); ItemSet itemSet4 = new ItemSet(items4); ItemSet
		  itemSet5 = new ItemSet(items5); ItemSet itemSet6 = new
		  ItemSet(items6); ItemSet itemSet7 = new ItemSet(items7); ItemSet
		  itemSet8 = new ItemSet(items8); ItemSet itemSet9 = new
		  ItemSet(items9);
		  
		  
		  //3. Make Transactions Transaction t1 = new Transaction (itemSet1);
		  Transaction t2 = new Transaction(itemSet2); Transaction t3 = new
		  Transaction(itemSet3); Transaction t4 = new Transaction(itemSet4);
		  Transaction t5 = new Transaction(itemSet5); Transaction t6 = new
		  Transaction(itemSet6); Transaction t7 = new Transaction(itemSet7);
		  Transaction t8 = new Transaction(itemSet8); Transaction t9 = new
		  Transaction(itemSet9); //4. Add Transactions to list
		  ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		  
		  transactions.add(t1); transactions.add(t2); transactions.add(t3);
		  transactions.add(t4); transactions.add(t5); transactions.add(t6);
		  transactions.add(t7); transactions.add(t8); transactions.add(t9);
		  //Create a TransactionSet from all transactions TransactionSet
		  tranSet = new TransactionSet(transactions);
		  //System.out.println(tranSet);
		  
		  //set number of transactions containing an itemset double
		  numberOfTransContainingItemSet = 2.0; int totalTransactions =
		  tranSet.getTransactionSet().size(); double minimumSupportLevel =
		  (numberOfTransContainingItemSet/totalTransactions)*totalTransactions;
		  System.out.println("Minimum Support Level: " + minimumSupportLevel);
		  double minimumConfidenceLevel = 0.5;
		  System.out.println("HARD CODED"); TransactionSet apriori1 =
		  doApriori(tranSet, minimumSupportLevel);
		 */

		System.out.println("TEXT FILE:  " + fileName1);
		TransactionSet textFileTranSet = getTransactionSetFromFile(fileName1);
		System.out.println(textFileTranSet);
		double numberOfTransContainingItemSet = 2.0;
		double totalTransactions = textFileTranSet.getTransactionSet().size();
		double minimumSupportLevel = (numberOfTransContainingItemSet / totalTransactions)* totalTransactions;
		double minimumConfidenceLevel = 0.5;
		
		
		TransactionSet apriori2 = doApriori(textFileTranSet, minimumSupportLevel);
		System.out.println(apriori2);
		RuleSet ruleset = generateRuleSet(textFileTranSet, apriori2, minimumConfidenceLevel);
		
		System.out.println("RULESET: \n"+ ruleset);
		
		
	}
	
	
	public static ArrayList<ItemSet> findSubsets(ItemSet candidates, ArrayList<ItemSet> ps)
	{
		ArrayList<ItemSet> powerSet = ps;
		if(!powerSet.contains(candidates) )
		{
			//System.out.println("adding to power set: " + candidates);
			powerSet.add(candidates);
			//System.out.println(powerSet);
		}
		
		
		
		for(int i = 0; i<candidates.getItems().size(); i++)
		{
			ArrayList<Item> subset = new ArrayList<Item>(candidates.getItems());
			subset.remove(i);
			
			ItemSet itemSubset = new ItemSet(subset);			
			findSubsets(itemSubset, powerSet);
		}	
		return powerSet;
	}
	
	
	
	public static ArrayList<Transaction> findSubsets2(Transaction candidates, ArrayList<Transaction> ps, int kitems)
	{
		ArrayList<Transaction> powerSet = ps;
		if(!powerSet.contains(candidates) && candidates.getItemSet().getItems().size()<=kitems)
		{
			System.out.println("adding to power set: " + candidates);
			powerSet.add((candidates));
			System.out.println(powerSet);
		}
		
		
		
		for(int i = 0; i<candidates.getItemSet().getItems().size(); i++)
		{
			Transaction subset = new Transaction(candidates.getItemSet());
			subset.getItemSet().getItems().remove(i);
			
			Transaction itemSubset = new Transaction(subset.getItemSet());			
			findSubsets2(itemSubset, powerSet, kitems);
		}	
		
		
		return  powerSet;
	}
	
	

	private static RuleSet generateRuleSet(TransactionSet originalTranSet, TransactionSet aprioriSet,	double minimumConfidenceLevel) {
		// TODO Auto-generated method stub
		
		
		
		ArrayList<Rule> allRules = new ArrayList<Rule>();
			for(Transaction transaction: aprioriSet.getTransactionSet()){
				ArrayList<ItemSet> itemList = new ArrayList<ItemSet>();
				itemList = findSubsets(transaction.getItemSet(), itemList);//get all subsets
				
				
				
				for(ItemSet subset : itemList){
					
					//System.out.println(subset + "-->"+transaction.getItemSet());
					//System.out.print(originalTranSet.findSupportLevel(transaction.getItemSet()));
					//System.out.print("/" + originalTranSet.findSupportLevel(subset));
					double confidence = (originalTranSet.findSupportLevel(transaction.getItemSet()))/(originalTranSet.findSupportLevel(subset))*100.0;
					//System.out.println("="+confidence);
					
					if(confidence >= minimumConfidenceLevel){
						Rule newRule = new Rule();
						newRule.setAntecedent(subset);
						ArrayList<Item> items = new ArrayList<Item>(transaction.getItemSet().getItems());
						ItemSet consequent = new ItemSet(items);
						for(int i =0; i<subset.getItems().size(); i++)
						{
							System.out.println("REMOVE:" + subset.getItems().get(i));
							consequent.getItems().remove(subset.getItems().get(i));
						}
						
						
						newRule.setConsequent(consequent);
						newRule.setActualConfidenceLevel(confidence);
						newRule.setSupport(originalTranSet.findSupportLevel(transaction.getItemSet()));
						if(newRule.getAntecedent().getItems().size() > 0 && newRule.getConsequent().getItems().size() >0){
							System.out.println(newRule);
							allRules.add(newRule);
						}
					}
				
			}
			}
		

		
		
		
		
		
		
		return new RuleSet(allRules);
	}
	

	/* METHOD NOTES: */

	public static TransactionSet doApriori(TransactionSet tranSet,	double minimumSupportLevel) {

		ItemSet uniqueItems = tranSet.getUniqueItems();
		
		/*
		System.out.println("uniqueItems: " + uniqueItems);
		ArrayList<ItemSet> itemList = new ArrayList<ItemSet>();
		itemList = findSubsets(uniqueItems, itemList, 2);
		System.out.println("ITEMLIST: " + itemList);
		TransactionSet psTransSet = new TransactionSet();
		for(int i=0; i<itemList.size(); i++)
		{
			Transaction temp = new Transaction(itemList.get(i));
			psTransSet.add(temp);
		}
		
		System.out.println("TransLIST: " + psTransSet);
		 */
		
		
		// System.out.println("UNIQUE:" +uniqueItems);

		TransactionSet large = new TransactionSet(); // resultant large ItemSets
		TransactionSet iterations = new TransactionSet(); // large ItemSet in
															// each iteration
		TransactionSet candidates = new TransactionSet(); // candidate ItemSet
															// in each iteration

		// Part 1: Generate all candidate single-item sets
		// first iteration (1-item ItemSets)
		for (int i = 0; i < uniqueItems.getItems().size(); i++) {
			Item candidate = uniqueItems.getItems().get(i);
			ItemSet itemSet = new ItemSet();
			itemSet.add(candidate);
			candidates.add(new Transaction(itemSet));

		}
		System.out.println("candidates: " + candidates);
		
		
		// next iterations
		int k = 2;
		while (candidates.getTransactionSet().size() != 0) {

			// set iterations from candidates (pruning)
			iterations.getTransactionSet().clear();
			// look at each transaction from the candidates
			for (Transaction transaction : candidates.getTransactionSet()) {
				double supportLevel = tranSet.findSupportLevel(transaction
						.getItemSet());
				transaction.getItemSet().setSupport(supportLevel);

				if (transaction.getItemSet().getSupport() >= minimumSupportLevel) {
					iterations.add(transaction);
					if (transaction.getItemSet().getItems().size() > 1) {
						large.add(transaction);

					}

				}
			}

			// set candidates for next iteration (find supersets of iterations)
			candidates.getTransactionSet().clear();
			/*
			//ArrayList<ItemSet> itemList = new ArrayList<It>
		    //ArrayList<ItemSet> itemSetPowerSet = new ArrayList<ItemSet>(findSubsets(iterations.getUniqueItems(), new ArrayList<ItemSet>(),k));// get k-item subsets
			
			//candidates.setTransactionSet(psTransSet);
			 * */
			 
			candidates.setTransactionSet(findSubsetsTrans(iterations.getUniqueItems(), k));// get k-item subsets

			k += 1;

		}
		// System.out.println("LARGE:" +large);
		return large;

	}
	
	
	
	public static TransactionSet generateTranSet(TransactionSet ts){
		ArrayList<ItemSet> itemList = new ArrayList<ItemSet>();
		itemList = findSubsets(ts.getUniqueItems(), itemList);
	    ArrayList<Transaction> psTransSet = new ArrayList<Transaction>();
		for(int i=0; i<itemList.size(); i++)
		{
			Transaction temp = new Transaction(itemList.get(i));
			psTransSet.add(temp);
		}
		return new TransactionSet(psTransSet);
	}

	/* METHOD NOTES: */

	private static ArrayList<Transaction> findSubsetsTrans(ItemSet itemSet, int k) {

		ArrayList<Transaction> allSubsets = new ArrayList<Transaction>();
		int subsetCount = (int) Math.pow(2, itemSet.getItems().size());
		// System.out.println("SubsetCount: " + subsetCount);
		for (int i = 0; i < subsetCount; i++) {
			ItemSet subset = new ItemSet();
			for (int bitIndex = 0; bitIndex < itemSet.getItems().size(); bitIndex++) {
				if (getBit(i, bitIndex) == 1) {
					subset.add(itemSet.getItems().get(bitIndex));

				}

			}

			if (subset.getItems().size() == k - 1) {
				allSubsets.add(new Transaction(subset));
			}
		}

		return allSubsets;
	}

	/* METHOD NOTES: */

	private static int getBit(int value, int position) {

		int bit = value & (int) Math.pow(2, position);
		if (bit > 0) {
			return 1;
		} else {
			return 0;
		}

	}

	/* METHOD NOTES: */

	public static TransactionSet getTransactionSetFromFile(String fileName) {

		TransactionSet allTransactions = new TransactionSet();
		try {
			ReadFile file = new ReadFile(fileName);
			String[] transactionSetLines = file.openFile();

			String pattern = "([A-Z])";
			Pattern regex = Pattern.compile(pattern);

			for (int i = 0; i < transactionSetLines.length; i++) {

				Scanner scanner = new Scanner(transactionSetLines[i]);
				scanner.useDelimiter("[^A-Za-z0-9]+");

				// make a new ItemSet to store
				ItemSet itemset = new ItemSet();

				while (scanner.hasNext()) {

					// get the item one by one
					String itemName = scanner.next();
					// System.out.println("ItemName: " + itemName);
					Item nextItem = new Item(itemName);

					itemset.add(nextItem);

				}// end of while
					// create a new transaction from the itemSet
				Transaction nextTransaction = new Transaction(itemset);

				// add the finished transaction to the total TransactionSet
				allTransactions.add(nextTransaction);
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return allTransactions;

	}
}
