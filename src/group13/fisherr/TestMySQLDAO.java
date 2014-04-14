package group13.fisherr;



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




	String query = "SELECT * FROM Vendor;";
	int result = 2;
	result = SQL.execute(query);
//	System.out.println(result);
	assertNotSame(result, 1);
	SQL.disconnect();
//		
	}


	//Executes a select query
	public void testExecute(){
		MySQLDAO SQL = new MySQLDAO();
		SQL.connect();




		String query = "SELECT * FROM Vendor;";
		int result = 2;
		result = SQL.execute(query);
//		System.out.println(result);
		assertNotSame(result, 1);
		SQL.disconnect();
	}
	/*
	public void testUpdate(){
	MySQLDAO SQL = new MySQLDAO();
	SQL.connect();
	
	String vendorName = "TestMart";
	String query = "INSERT INTO Vendor (VendorName) Values(\""+vendorName+"\")";
	int result = 2;
	result = SQL.executeUpdate(query);
	System.out.println(result);
	assertNotSame(result, 0);
	SQL.disconnect();
}
*/


	public void testDisconnect(){
		MySQLDAO SQL = new MySQLDAO();
		SQL.connect();
		SQL.disconnect();


		String query = "SELECT * FROM Vendor;";
		int result = 2;
		result = SQL.execute(query);
		System.out.println("Result" + result);
		//Not connected to database so should give an error, which makes result = 1
		assertEquals(result, 1);






		SQL.connect();
		int result2 = 2;
		result2 = SQL.execute(query);
		System.out.println("Result" + result2);
		//Same thing but now disconnect is after, so it should not be 1.
		assertFalse(result2 == 1);
		SQL.disconnect();




	}




}
