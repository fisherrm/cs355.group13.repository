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
		/* Make test itemSet */
		ArrayList<Item> itemList = new ArrayList<Item>();
		Item item1 = new Item ("Cookies");
		Item item2 = new Item ("Milk");
		itemList.add(item1);
		itemList.add(item2);
		ItemSet itemSet = new ItemSet();
		itemSet.setItems(itemList);
		/* End of making test itemSet */


		//test to make sure the to string includes the 2 items
		String itemSetString = itemSet.toString();
		assertTrue(itemSetString.contains("Milk")&& itemSetString.contains("Cookies"));
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
		/* Make test itemSet */
		ArrayList<Item> itemList = new ArrayList<Item>();
		Item item1 = new Item ("Cookies");
		Item item2 = new Item ("Milk");
		itemList.add(item1);
		itemList.add(item2);
		ItemSet itemSet = new ItemSet();
		itemSet.setItems(itemList);
		/* End of making test itemSet */
		Item item3 = new Item ("Bread");
		itemSet.add(item3);
		//test to make sure itemSet contains item3, the item that was added via the .add
		assertTrue(itemSet.containsItem(item3));
	}
	public void testEquals(){
		/* Make test itemSet */
		ArrayList<Item> itemList = new ArrayList<Item>();
		Item item1 = new Item ("Cookies");
		Item item2 = new Item ("Milk");
		itemList.add(item1);
		itemList.add(item2);
		ItemSet itemSet = new ItemSet();
		itemSet.setItems(itemList);
		/* End of making test itemSet */


		/* Make test itemSet */
		ArrayList<Item> itemListCopy = new ArrayList<Item>();
		Item item1Copy = new Item ("Cookies");
		Item item2Copy = new Item ("Milk");
		itemListCopy.add(item1Copy);
		itemListCopy.add(item2Copy);
		ItemSet itemSetCopy = new ItemSet();
		itemSetCopy.setItems(itemListCopy);
		/* End of making test itemSet */


		//make sure the copy lists is = to the 1st list
		assertTrue(itemSet.equals(itemSetCopy));


		//make  a change and check to make sure it's now false
		Item item3 = new Item ("Bread");
		itemSet.add(item3);
		assertFalse(itemSet.equals(itemSetCopy));


	}
	public void testContainsItemSet(){
		/* Make test itemSet */
		ArrayList<Item> itemList = new ArrayList<Item>();
		Item item1 = new Item ("Cookies");
		Item item2 = new Item ("Milk");
		Item item3 = new Item ("Bread");
		itemList.add(item1);
		itemList.add(item2);
		itemList.add(item3);
		ItemSet itemSet = new ItemSet();
		itemSet.setItems(itemList);
		/* End of making test itemSet */


		/* Make test itemSet (this will be used to check if the 1st one contains this one) */
		ArrayList<Item> itemListCopy = new ArrayList<Item>();
		Item item1Copy = new Item ("Cookies");
		Item item2Copy = new Item ("Milk");
		itemListCopy.add(item1Copy);
		itemListCopy.add(item2Copy);
		ItemSet itemSetCopy = new ItemSet();
		itemSetCopy.setItems(itemListCopy);
		/* End of making test itemSet */


		//check to make sure the 2nd item set is contained in the 1st
		assertTrue(itemSet.containsItemSet(itemSetCopy));


		//make a change in the itemset so it won't be contained in it
		Item item4 = new Item ("Candy");
		itemSetCopy.add(item4);
		assertFalse(itemSet.containsItemSet(itemSetCopy));
	}
	public void testRemove(){
		/* Make test itemSet */
		ArrayList<Item> itemList = new ArrayList<Item>();
		Item item1 = new Item ("Cookies");
		Item item2 = new Item ("Milk");
		Item item3 = new Item ("Bread");
		itemList.add(item1);
		itemList.add(item2);
		itemList.add(item3);
		ItemSet itemSet = new ItemSet();
		itemSet.setItems(itemList);
		/* End of making test itemSet */


		/* Make test itemSet */
		ArrayList<Item> itemListCopy = new ArrayList<Item>();
		Item item1Copy = new Item ("Cookies");
		Item item2Copy = new Item ("Milk");
		itemListCopy.add(item1Copy);
		itemListCopy.add(item2Copy);
		ItemSet itemSetCopy = new ItemSet();
		itemSetCopy.setItems(itemListCopy);
		/* End of making test itemSet */


		//Make sure these are not equal
		assertFalse(itemSet.equals(itemSetCopy));


		//remove the item 3 so they will be 
		itemSet.remove(item3);
		assertTrue(itemSet.equals(itemSetCopy));
	}


}
