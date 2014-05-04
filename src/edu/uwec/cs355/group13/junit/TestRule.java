package edu.uwec.cs355.group13.junit;



import junit.framework.TestCase;
import edu.uwec.cs355.group13.service.ItemSet;
import edu.uwec.cs355.group13.service.Rule;


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
public class TestRule extends TestCase {


	public void testRule() {
	ItemSet itemSet = new ItemSet();
	ItemSet itemSet2 = new ItemSet();
	double cons = 0;
	double antec = 0;
	Rule rule = new Rule();
	assertNotNull(rule);
	Rule rule2 = new Rule(itemSet, itemSet2, cons, antec);


	assertNotNull(rule2);
	}
	public void testGetSetAntecedent(){
		ItemSet itemSet = new ItemSet();
		Rule rule = new Rule();
		rule.setAntecedent(itemSet);
		assertEquals(itemSet, rule.getAntecedent());
	}
	public void testGetSetConsequent(){
		ItemSet itemSet = new ItemSet();
		Rule rule = new Rule();
		rule.setConsequent(itemSet);
		assertEquals(itemSet, rule.getConsequent());
	}


	public void testGetSetActualConfidenceLevel(){
		double testDouble = 5;
		Rule rule = new Rule();
		rule.setActualConfidenceLevel(testDouble);
		assertEquals(testDouble, rule.getActualConfidenceLevel());
	}
	public void testGetSetSupport(){
		double testDouble = 5;
		Rule rule = new Rule();
		rule.setSupportLevel(testDouble);
		assertEquals(testDouble, rule.getSupportLevel());	
	}
	public void testToString(){
		Rule rule = new Rule(); 
		double testDouble1 = 5; //ActualConfidence
		double testDouble2 = 10; // Support
		rule.setActualConfidenceLevel(testDouble1);
		rule.setSupportLevel(testDouble2);
		System.out.println(rule);
		//Our toString prints the ActualConfidence, but not the support so we test to make sure it prints 5 and not 10.
		assertTrue(!(rule.toString().contains("10")) && rule.toString().contains("5"));
	}


}

