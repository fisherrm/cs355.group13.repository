/*
 * MySQLDAO - DAO class that persists to a MySQL database
 * 
 * Created by Paul J. Wagner, 2/28/2013
 */
package group13.fisherr;

public class MySQLDAO implements DAOInterface {
	// methods
	public void connect() {
		// load MySQL driver
		// create connection to MySQL database
	}

	public int execute(String query) {
		int errorCode = 0;
		System.out.println("MySQL DAO execute");
		// use connection to execute query,
		//    get resultset
		// process resultset
		// set errorCode
		return errorCode;
	}
	
	public void disconnect() {
		// disconnect MySQL connection, statement, resultset
	}
}
