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

public class TestMySQLDAO extends TestCase {
	
//	public void TestConnect(){
//		MySQLDAO SQL = new MySQLDAO();
//		SQL.connect();
//		
//	}
//	public void TestUpdate(){
//		
//		
//	}
	public void TestExecute(){
		MySQLDAO SQL = new MySQLDAO();
		SQL.connect();
		

		String vendorName = "TestMart";
		String query = "INSERT INTO Vendor (VendorName) Values(\""+vendorName+"\")";
		int result = 2;
		result = SQL.execute(query);
		System.out.println(result);
		assertEquals(result, 0);
		SQL.disconnect();
	}
//	public void TestDisconnect(){
//		MySQLDAO SQL = new MySQLDAO();
//		SQL.connect();
//		SQL.disconnect();
//
//		String vendorName = "TestMart";
//		String query = "INSERT INTO Vendor (VendorName) Values(\""+vendorName+"\")";
//		int result = 2;
//		result = SQL.execute(query);
//		System.out.println(result);
//		assertEquals(result, 1);
//	}
	

}
