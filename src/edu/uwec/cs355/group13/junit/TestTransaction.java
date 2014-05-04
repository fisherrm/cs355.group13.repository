package edu.uwec.cs355.group13.junit;

import java.util.ArrayList;

import service.Item;
import service.ItemSet;
import service.Transaction;


import junit.framework.TestCase;


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
public class TestTransaction extends TestCase { 








	ItemSet itemSet = new ItemSet();
	public void testTransaction() {
	ItemSet item = new ItemSet();
	Transaction transaction = new Transaction(item);
	assertNotNull(transaction);
	}
	public void testGetItemSet(){
		/*Makes a test itemset*/
		Item singleItem1 = new Item("egg");
		Item singleItem2 = new Item("cheese");
		Item singleItem3 = new Item("milk");
		ArrayList<Item> itemlist = new ArrayList<Item>();
		itemlist.add(singleItem1);
		itemlist.add(singleItem2);
		itemlist.add(singleItem3);
		ItemSet item = new ItemSet(itemlist);
		/* end of making test itemset */


		ItemSet item2 = new ItemSet();


		Transaction transaction = new Transaction(item);	
		item2 = transaction.getItemSet();
		assertEquals(item, item2);
	}
	public void TestSetItemSet() {
		/*Makes a test itemset*/
		Item singleItem1 = new Item("egg");
		Item singleItem2 = new Item("cheese");
		Item singleItem3 = new Item("milk");
		ArrayList<Item> itemlist = new ArrayList<Item>();
		itemlist.add(singleItem1);
		itemlist.add(singleItem2);
		itemlist.add(singleItem3);
		ItemSet item = new ItemSet(itemlist);
		/* end of making test itemset */
		ItemSet emptyItem = new ItemSet();
		//Make empty transaction, set it to item, then see if item is the same as what
		// it was set to
		Transaction transaction = new Transaction(emptyItem);	
		transaction.setItemSet(item);
		assertEquals(item, transaction.getItemSet());


	}
	
	public void testGetSetDate(){
		/*Makes a test itemset*/
		ItemSet item = new ItemSet();
		/* end of making test itemset */
		Transaction transaction = new Transaction(item);	
		String date = "2014-04-04 12:00:00";//default date
		transaction.setDate("2014-04-04 12:00:00");
		assertEquals(date, transaction.getDate());		
	}
	public void testToString(){
		/*Makes a test itemset*/
		Item singleItem1 = new Item("egg");
		Item singleItem2 = new Item("cheese");
		Item singleItem3 = new Item("milk");
		ArrayList<Item> itemlist = new ArrayList<Item>();
		itemlist.add(singleItem1);
		itemlist.add(singleItem2);
		itemlist.add(singleItem3);
		ItemSet item = new ItemSet(itemlist);
		/* end of making test itemset */
		Transaction transaction = new Transaction(item);
		System.out.println(transaction);
		String output = transaction.toString();
		assertTrue(output.contains("egg") && output.contains("cheese") && output.contains("milk"));


	}
	public void testEquals(){
		/*Makes a test itemset*/
		Item singleItem1 = new Item("egg");
		Item singleItem2 = new Item("cheese");
		Item singleItem3 = new Item("milk");
		ArrayList<Item> itemlist = new ArrayList<Item>();
		itemlist.add(singleItem1);
		itemlist.add(singleItem2);
		itemlist.add(singleItem3);
		ItemSet item = new ItemSet(itemlist);
		/* end of making test itemset */
		Transaction transaction1 = new Transaction(item);
		/*Makes a test2 transaction*/
		ArrayList<Item> itemlist2 = new ArrayList<Item>();
		itemlist2.add(singleItem1);
		itemlist2.add(singleItem2);
		itemlist2.add(singleItem3);
		ItemSet item2 = new ItemSet(itemlist2);
		/* end of making test2 itemset */
		Transaction transaction2 = new Transaction(item2);
		assertTrue(transaction1.equals(transaction2));
		Item singleItem4 = new Item("beer");
		itemlist2.add(singleItem4);
		transaction2.setItemSet(item2);
		assertFalse(transaction1.equals(transaction2));
	}


}
