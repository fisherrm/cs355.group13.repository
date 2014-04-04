/*
 * MockDAO - mock data access object class
 * 
 * Created by Paul J. Wagner, 2/28/2013
 */
package group13.fisherr;

import java.sql.ResultSet;

public class MockDAO implements DAOInterface {
	// methods
	public void connect() {
		// do nothing
	}

	public int execute(String query) {
		// return fake error code
		System.out.println("mock DAO execute");
		return 0;
	}
	
	public void disconnect() {
		// do nothing
	}

	

	@Override
	public int executeUpdate(String query) {
		// TODO Auto-generated method stub
		return 0;
	}
}
