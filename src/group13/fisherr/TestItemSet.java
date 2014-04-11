package group13.fisherr;


import java.util.ArrayList;


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


public class TestItemSet extends TestCase {


	public void testItemSet (){
		ItemSet itemSet = new ItemSet();
		assertNotNull(itemSet);
	}
	public void testGetSetItemSet(){
		ArrayList<Item> itemList = new ArrayList<Item>();
		Item item1 = new Item ("Cookies");
		Item item2 = new Item ("Milk");
		itemList.add(item1);
		itemList.add(item2);
		ItemSet itemSet = new ItemSet();
		itemSet.setItems(itemList);
		ArrayList<Item> itemList2 = itemSet.getItems(); 
		//Make test itemList then set itemSet to that. 


		//test that itemSet.getItems is the same as the initial itemList
		assertEquals(itemList, itemList2);
	}
	public void testGetSetSupport(){
		ItemSet itemSet = new ItemSet();
		double startSupport = .5;
		itemSet.setSupportLevel(startSupport);
		double finalSupport;
		finalSupport = itemSet.getSupportLevel();
		assertEquals (finalSupport, startSupport);
	}
	public void testToString(){


	}
	public void testContainsItem(){
		/* Make test itemSet */
		ArrayList<Item> itemList = new ArrayList<Item>();
		Item item1 = new Item ("Cookies");
		Item item2 = new Item ("Milk");
		itemList.add(item1);
		itemList.add(item2);
		ItemSet itemSet = new ItemSet();
		itemSet.setItems(itemList);
		/* End of making test itemSet */


		//Test to make sure .containsItem gets item 1 and 2
		assertTrue(itemSet.containsItem(item1) && itemSet.containsItem(item2));


		//Test to make sure .containsItem doesn't get random other items
		Item item3 = new Item ("Fake item");
		assertFalse(itemSet.containsItem(item3));
	}
	//This is adding an item
	public void testAdd(){


	}


}
