package junit.tests;



import service.MySQLDAO;
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


public class TestMySQLDAO extends TestCase {

	//This is the same as Execute, but in order to select something we must be connected
	//so if this runs, then connect works.
	public void testConnect(){
		MySQLDAO SQL = new MySQLDAO();
		SQL.connect();
		String validQuery = "SELECT MIN(TransactionSet_ID) FROM TransactionSet;";
		int result = SQL.execute(validQuery);
		//System.out.println("1st result: " + result);
		assertEquals(1, result);
		SQL.disconnect();
		
	}


	public void testExecuteUpdate(){
		MySQLDAO SQL = new MySQLDAO();
		SQL.connect();
		//test a valid INSERT for Transaction Set
		String date = "2014-04-04 12:00:00";
		String startDate = "STR_TO_DATE(\""+date+"\",\"%Y-%m-%d %H:%i:%S\")";
		String endDate = "STR_TO_DATE(\""+date+"\",\"%Y-%m-%d %H:%i:%S\")";
		String insertQuery = "INSERT INTO TransactionSet (TransactionSetStartDate, TransactionSetEndDate) Values("+startDate+","+endDate+")";
		
		int result = SQL.executeUpdate(insertQuery);
		//System.out.println("1st result: " + result);
		//a resultCode of 1 is a sucessful INSERT
		assertEquals(1, result);
		
		//invalidInsertQuery
		String invalidInsertQuery = "INSERT INTO TransactionSet (TransactionSetStartDate, TransactionSetEndDate)";
		int result2 = SQL.executeUpdate(invalidInsertQuery);
		//System.out.println("2nd result: " + result2);
		//a resultCode of -1 is a failed INSERT
		assertEquals(-1, result2);
		
	}
	//Executes a select query
	public void testExecute(){
		
		MySQLDAO SQL = new MySQLDAO();
		SQL.connect();
		//test for a valid select query
		//the min transcation set id will be 1 (as long as a transaction set is inserted
		String validQuery = "SELECT MIN(TransactionSet_ID) FROM TransactionSet;";
		int result = SQL.execute(validQuery);
		//System.out.println("1st result: " + result);
		assertEquals(1, result);
		//test the that the invalid select query
		String invalidQuery = "SELECT TransactionSet;";
		int result2 = SQL.execute(invalidQuery);
		//System.out.println("2nd result: " + result2);
		assertEquals(-1, result2);
		SQL.disconnect();
		
	}
		


	public void testDisconnect(){
		MySQLDAO SQL = new MySQLDAO();
		SQL.connect();
		SQL.disconnect();
		String query = "SELECT MIN(TransactionSet_ID) FROM Transaction;";
		//int result = 2;
		int result = SQL.execute(query);
		//System.out.println("First Result: " + result);
		//Not connected to database so should give an error, which makes result = -1
		//assertEquals(result, 1);
		assertEquals(-1, result);
		SQL.connect();
		int result2 = SQL.execute(query);
		//System.out.println("Second Result: " + result2);
		//Same thing but now disconnect is after, so it should be an transaction set id of 1.
		assertEquals(1, result2);
		SQL.disconnect();

	}




}
