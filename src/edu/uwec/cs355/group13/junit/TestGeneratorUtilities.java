package edu.uwec.cs355.group13.junit;


import junit.framework.TestCase;


import java.util.ArrayList;

import service.Item;
import service.ItemSet;
import service.Rule;
import service.Transaction;
import edu.uwec.cs355.group13.common.GeneratorUtilities;
import edu.uwec.cs355.group13.common.RuleSet;
import edu.uwec.cs355.group13.common.TransactionSet;


/*
 * assert methods
 *  assertEquals (message, expected, actual)	
 *  assertEquals (expected, actual, delta)
 *  assertEquals (expected, actual)
 *  // many more versions of assertEquals(...)
 *  assertTrue (condition)
 *  assertFalse (condition)
 *  assertNotNull (object-variable)
 *  assertNull (object-variable)
 *  assertSame (object1, object2)
 *  assertNotSame (object1, object2)
 *  fail (message)
 */
public class TestGeneratorUtilities extends TestCase{
	
	
	public void testGetSetMinimumSupportLevel(){
		GeneratorUtilities generator = new GeneratorUtilities();
		generator.setMinimumSupportLevel(0.5);
		assertEquals(0.5, generator.getMinimumSupportLevel());
		

	}
	
	public void testGetSetMinimumConfidenceLevel(){
		GeneratorUtilities generator = new GeneratorUtilities();
		generator.setMinimumConfidenceLevel(0.5);
		assertEquals(0.5, generator.getMinimumConfidenceLevel());
	}

	public void testGetSetFilepath(){
		GeneratorUtilities generator = new GeneratorUtilities();
		generator.setFilepath("filepath");
		assertEquals("filepath", generator.getFilepath());
	}
	
	public void testValidateTranSet(){
		GeneratorUtilities generator = new GeneratorUtilities();
		TransactionSet ts = new TransactionSet();
		ts.add(new Transaction());
		assertTrue(generator.validateTranSet(ts));
		ts = new TransactionSet();
		assertFalse(generator.validateTranSet(ts));

	}
	
	public void testValidateRuleSetLevel(){
		GeneratorUtilities generator = new GeneratorUtilities();
		RuleSet rs = new RuleSet();
		rs.add(new Rule());
		assertTrue(generator.validateRuleSet(rs));
		rs = new RuleSet();
		assertFalse(generator.validateRuleSet(rs));
		
	}


	public void testValidateMinLevel(){
		GeneratorUtilities generator = new GeneratorUtilities();
		double negative = -.1;
		double bigNum = 10;
		double validNumber = .5;


		assertTrue((!(generator.validateMinLevel(negative))) && (!(generator.validateMinLevel(bigNum))) && (generator.validateMinLevel(validNumber)));
	}
	public void testFindSubsets(){
		Item item1 = new Item("Bread");
		Item item2 = new Item("Water");
		Item item3 = new Item("Milk");
		Item item4 = new Item("Eggs");
		ArrayList<Item> itemlist = new ArrayList<Item>();
		itemlist.add(item1);
		itemlist.add(item2);
		itemlist.add(item3);
		itemlist.add(item4);
		ItemSet itemSet = new ItemSet(itemlist);
		GeneratorUtilities generator = new GeneratorUtilities();


		ArrayList<ItemSet> itemList = new ArrayList<ItemSet>();
		itemList = generator.findSubsets(itemSet, itemList);//get all subsets
//		System.out.println(itemList);
	}
	public void testGenerateRuleSet(){
		/* Make test TransactionSet */
		Item item2 = new Item("Water");
		Item item3 = new Item("Milk");
		Item item4 = new Item("Beer");
		ArrayList<Item> itemlist = new ArrayList<Item>();
		itemlist.add(item2);
		itemlist.add(item3);
		itemlist.add(item4);
		ItemSet itemSet = new ItemSet(itemlist);
		Item itemA2 = new Item("Water");
		Item itemA3 = new Item("Milk");
		Item itemA4 = new Item("Eggs");
		ArrayList<Item> itemlist2 = new ArrayList<Item>();
		itemlist2.add(itemA2);
		itemlist2.add(itemA3);
		itemlist2.add(itemA4);
		ItemSet itemSet2 = new ItemSet(itemlist2);
		Item itemB3 = new Item("Milk");
		Item itemB4 = new Item("Eggs");
		ArrayList<Item> itemlist3 = new ArrayList<Item>();
		itemlist3.add(itemB3);
		itemlist3.add(itemB4);
		ItemSet itemSet3 = new ItemSet(itemlist3);
		GeneratorUtilities generator = new GeneratorUtilities();
		Transaction transaction1 = new Transaction(itemSet);
		Transaction transaction2 = new Transaction(itemSet2);
		Transaction transaction3 = new Transaction(itemSet3);
		ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
		transactionList.add(transaction1);
		transactionList.add(transaction2);
		transactionList.add(transaction3);
		TransactionSet transactionSet = new TransactionSet(transactionList);
		/* End of test Transaction set */
		double minConf = .5;
		double minSup = .5;
		TransactionSet aprioriSet = generator.doApriori(transactionSet, minSup);
		RuleSet ruleSet  = generator.generateRuleSet(transactionSet, aprioriSet, minConf);
//		System.out.println("Rule set " + ruleSet);
		//Get the individual rules
		Rule rule1 = ruleSet.getRuleSet().get(0);
//		Rule rule2 = ruleSet.getRuleSet().get(1);
//		Rule rule3 = ruleSet.getRuleSet().get(2);
//		Rule rule4 = ruleSet.getRuleSet().get(3);


		//Just checks if the first line is If milk THEN water (.6667), which it should be. Could make more tests, but if first line is right then it should be ok.
		assertTrue(rule1.getActualConfidenceLevel() == 0.6667 && rule1.getAntecedent().containsItem(item3) && rule1.getConsequent().containsItem(itemA2));


	}
	//this also basically tests findSubsetsApriori() so not needed to test that also
	public void testDoApriori(){
		/* Make test TransactionSet */
		Item item2 = new Item("Water");
		Item item3 = new Item("Milk");
		Item item4 = new Item("Beer");
		ArrayList<Item> itemlist = new ArrayList<Item>();
		itemlist.add(item2);
		itemlist.add(item3);
		itemlist.add(item4);
		ItemSet itemSet = new ItemSet(itemlist);
		Item itemA2 = new Item("Water");
		Item itemA3 = new Item("Milk");
		Item itemA4 = new Item("Eggs");
		ArrayList<Item> itemlist2 = new ArrayList<Item>();
		itemlist2.add(itemA2);
		itemlist2.add(itemA3);
		itemlist2.add(itemA4);
		ItemSet itemSet2 = new ItemSet(itemlist2);
		Item itemB3 = new Item("Milk");
		Item itemB4 = new Item("Eggs");
		ArrayList<Item> itemlist3 = new ArrayList<Item>();
		itemlist3.add(itemB3);
		itemlist3.add(itemB4);
		ItemSet itemSet3 = new ItemSet(itemlist3);
		GeneratorUtilities generator = new GeneratorUtilities();
		Transaction transaction1 = new Transaction(itemSet);
		Transaction transaction2 = new Transaction(itemSet2);
		Transaction transaction3 = new Transaction(itemSet3);
		ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
		transactionList.add(transaction1);
		transactionList.add(transaction2);
		transactionList.add(transaction3);
		TransactionSet transactionSet = new TransactionSet(transactionList);
		/* End of test Transaction set */
		double minSup = .5;
		TransactionSet aprioriSet = generator.doApriori(transactionSet, minSup);
		System.out.println("AprioriSet " + aprioriSet);
		ItemSet uniqueItems = aprioriSet.getUniqueItems();
		//Makes sure the items Water, Milk, and Eggs are in the aprioriSet, which they should be and that the size is 3 so only those 3 items. (This could use a bit of work possibly)
		assertTrue(uniqueItems.containsItem(item2) && uniqueItems.containsItem(item3) && uniqueItems.containsItem(itemA4) && uniqueItems.getItems().size() == 3);




	}
	public void testGetTransactionSetFromFile(){
		GeneratorUtilities generator = new GeneratorUtilities();
		String filename = "src/transactionSet_01.txt";
		TransactionSet transactionSet = new TransactionSet();
		transactionSet = generator.getTransactionSetFromFile(filename);
		ArrayList<Transaction> transactionList = transactionSet.getTransactionSet();
		System.out.println(transactionList);
		//make sure the first 4 lines are read right
		assertTrue(transactionList.get(0).toString().contains("A,B,E"));
		assertTrue(transactionList.get(1).toString().contains("B,D"));
		assertTrue(transactionList.get(2).toString().contains("B,C"));
		assertTrue(transactionList.get(3).toString().contains("A,B,D"));
		//test to make sure it's not all true
		assertFalse(transactionList.get(4).toString().contains("A,B,C"));
	}


}
