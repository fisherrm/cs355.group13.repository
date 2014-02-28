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
		double numberOfTransContainingItemSet = 2.0;
		double totalTransactions = textFileTranSet.getTransactionSet().size();
		double minimumSupportLevel = (numberOfTransContainingItemSet / totalTransactions)* totalTransactions;
		double minimumConfidenceLevel = 0.5;
		TransactionSet apriori2 = doApriori(textFileTranSet, minimumSupportLevel);
		System.out.println(apriori2);
		RuleSet ruleset = generateRuleSet(apriori2, minimumConfidenceLevel);
	}

	private static RuleSet generateRuleSet(TransactionSet apriori2,	double minimumConfidenceLevel) {
		// TODO Auto-generated method stub
		/*Put Adam's source Code here*/
		
		
		
		
		
		
		
		
		
		return null;
	}

	/* METHOD NOTES: */

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
			candidates.setTransactionSet(findSubsets(
					iterations.getUniqueItems(), k));// get k-item subsets

			k += 1;

		}
		// System.out.println("LARGE:" +large);
		return large;

	}

	/* METHOD NOTES: */

	private static ArrayList<Transaction> findSubsets(ItemSet itemSet, int k) {

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
