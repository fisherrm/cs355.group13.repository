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
		
		
		//1. Provide hardcoded text file paths for the file reader
		
		String fileName1 = "src/transactionSet_01.txt";
		String fileName2 = "src/transactionSet_02.txt";
		String fileName3 = "src/transactionSet_03.txt";
		// String fileName = "H://CS/CS 355/GROUP13/GROUP13.FISHERR/src/transactionSet_01.txt";
		// String fileName = "H://CS/CS 355/GROUP13/textFiles/test1.txt";
		
		//System.out.println("TEXT FILE:  " + fileName1);
		
		//2. Read the transaction set from the file
		TransactionSet textFileTranSet = getTransactionSetFromFile(fileName1);
		//System.out.println(textFileTranSet);
		
		//fix this number, this should be found in the text file
		double numberOfTransContainingItemSet = 2.0;
		double totalTransactions = textFileTranSet.getTransactionSet().size();
		
		//3. specify the minimumSupportLevel
		double minimumSupportLevel = (numberOfTransContainingItemSet / totalTransactions)* totalTransactions;
		
		//4. specify the minimumConfidenceLevel
		double minimumConfidenceLevel = 0.5;
		
		
		TransactionSet apriori = doApriori(textFileTranSet, minimumSupportLevel);
		//System.out.println(apriori);
		RuleSet ruleset = generateRuleSet(textFileTranSet, apriori, minimumConfidenceLevel);
		
		//5. Write ruleset to the output file
		System.out.println(ruleset);
		
		
	}
	
	/*METHOD NOTES
	 * 
	 * Use this method to find the subsets that are used in the generateRuleSet
	 * 
	 * */
	
	
	public static ArrayList<ItemSet> findSubsets(ItemSet candidates, ArrayList<ItemSet> ps)
	{
		
		ArrayList<ItemSet> powerSet = ps;
		
		if(!powerSet.contains(candidates) )	{
			powerSet.add(candidates);	
		}
		
		
		for(int i = 0; i<candidates.getItems().size(); i++){
			
			ArrayList<Item> subset = new ArrayList<Item>(candidates.getItems());
			subset.remove(i);
			ItemSet itemSubset = new ItemSet(subset);			
			findSubsets(itemSubset, powerSet);
		}
		
		return powerSet;
	}
	
	
	
	/*METHOD NOTES
	 * 
	 * Use this method to generate the association rules given the text files original transaction set, the A Priori generated algorithm
	 * and the minimum confidence level
	 * */
	
	

	private static RuleSet generateRuleSet(TransactionSet originalTranSet, TransactionSet aprioriSet,	double minimumConfidenceLevel) {
		
		ArrayList<Rule> allRules = new ArrayList<Rule>();
			for(Transaction transaction: aprioriSet.getTransactionSet()){
				ArrayList<ItemSet> itemList = new ArrayList<ItemSet>();
				
				itemList = findSubsets(transaction.getItemSet(), itemList);//get all subsets
				
				
				
				for(ItemSet subset : itemList){
					
					//System.out.println(subset + "-->"+transaction.getItemSet());
					//System.out.print(originalTranSet.findSupportLevel(transaction.getItemSet()));
					//System.out.print("/" + originalTranSet.findSupportLevel(subset));
					double confidence = (originalTranSet.findSupportLevel(transaction.getItemSet()))/(originalTranSet.findSupportLevel(subset));
					//System.out.println("="+confidence);
					
					if(confidence >= minimumConfidenceLevel){
						Rule newRule = new Rule();
						newRule.setAntecedent(subset);
						
						ArrayList<Item> items = new ArrayList<Item>(transaction.getItemSet().getItems());
						ItemSet consequent = new ItemSet(items);
						
						for(int i =0; i<subset.getItems().size(); i++)
						{
							
							consequent.getItems().remove(subset.getItems().get(i));
						}
						
						
						newRule.setConsequent(consequent);
						
						newRule.setActualConfidenceLevel(confidence);
						newRule.setSupport(originalTranSet.findSupportLevel(transaction.getItemSet()));
						if(newRule.getAntecedent().getItems().size() > 0 && newRule.getConsequent().getItems().size() >0){
							
							allRules.add(newRule);
						}
					}
				
			}
			}
		

		return new RuleSet(allRules);
	}
	

	/* METHOD NOTES: 
	 * 
	 * Use this method to take the transaction set and the mininumSupportLevel to produce the 1st filtered transaction set
	 * 
	 * */

	public static TransactionSet doApriori(TransactionSet tranSet,	double minimumSupportLevel) {

		ItemSet uniqueItems = tranSet.getUniqueItems();
		
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
		//System.out.println("candidates: " + candidates);
		
		
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
			candidates.setTransactionSet(findSubsetsApriori(iterations.getUniqueItems(), k));// get k-item subsets

			k += 1;

		}
		// System.out.println("LARGE:" +large);
		return large;

	}
	

	/*METHOD NOTES
	 * 
	 * this method is used to generate the subsets for the A Priori algorithm
	 */

	private static ArrayList<Transaction> findSubsetsApriori(ItemSet itemSet, int k) {

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

	/* METHOD NOTES: 
	 * 
	 * Used to read a transactions set from a file
	 * This is will need to have validation methods later
	 * */

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
