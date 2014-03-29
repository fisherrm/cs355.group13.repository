/*
 * MySQLDAO - DAO class that persists to a MySQL database
 * 
 * Created by Paul J. Wagner, 2/28/2013
 */
package group13.fisherr;

/*
 * MySQLDAO - DAO class that persists to a MySQL database
 * 
 * Created by Paul J. Wagner, 2/28/2013
 */



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLDAO implements DAOInterface {
	// methods
	Connection conn = null;//connection object
	Statement stmt = null;// SQL statement object
	ResultSet rset = null;// statement result set object
	// --- set the username and password
	String user = "CS355GROUP13";		// bypass call to readEntry	
	String pass = "U856314$";
	public void connect() {
		// load MySQL driver
		// create connection to MySQL database
		
		   // --- 1) get the Class object for the driver 
		   try
		   {
			  Class.forName ("com.mysql.jdbc.Driver");
			  System.out.println("Found/loaded MySQL Connector JDBC driver");	   
		   }
		   catch (ClassNotFoundException e)
		   {
			  System.out.println ("Could not get class object for Driver");
			  System.out.println(e.getMessage());
			  System.exit(1);
		   }

		   // --- 2) connect to database
		  
		   try
		   {
			  conn = DriverManager.getConnection("jdbc:mysql://dario.cs.uwec.edu/student",user,pass);
		   }
		   catch (SQLException sqle)
		   {
			  System.out.println ("Could not make connection to database");
			  System.out.println(sqle.getMessage());
			  System.exit(1);
		   }

		
		
		
		
		
	}

	public int execute(String query) {
		int errorCode = 0;
		System.out.println("MySQL DAO execute");
		// use connection to execute query,
		//    get resultset
		// process resultset
		// set errorCode
		
		
		// --- 3) prepare and execute statement
		   
		   try
		   {
			  stmt = conn.createStatement();
			  rset = stmt.executeQuery(query);
		   }
		   catch (Exception e)
		   {
			   System.out.println("Could not execute SQL statement");
			   System.out.println(e.getMessage());
			   System.exit(1);
			   errorCode = 1;
		   }

		   // --- 4) process result set
		   try {
			   while (rset.next()) {
				   System.out.println(rset.getString(1) + "  " +	// TODO: change to use metadata
								 	  rset.getString(2));
			   }     // --- end - while
		   }
		   catch (SQLException sqle) {
			   System.out.println("Could not process result set");
			   System.out.println(sqle.getMessage());
			   System.exit(1);
			   errorCode= 1;
		   }

		  
		   
	  

		
		
		
		return errorCode;
	}
	
	public void disconnect() {
		// disconnect MySQL connection, statement, resultset
		
		
		 // --- 5) clean up statement and connection
		   try {
			   rset.close();
			   stmt.close();
			   conn.close();
		   } 
		   catch (SQLException sqle) {
			   System.out.println("Could not close statement and connection");
			   System.out.println(sqle.getMessage());
			   System.exit(1);
		   }
		   
		   System.exit(0);

		
	}

	@Override
	public int executeForResultSet(String query) {
		// TODO Auto-generated method stub
		
		System.out.println("MySQL DAO execute");
		// use connection to execute query,
		//    get resultset
		// process resultset
		// set errorCode
		
		int id = 0;
		// --- 3) prepare and execute statement
		   
		   try
		   {
			  stmt = conn.createStatement();
			  rset = stmt.executeQuery(query);
		   }
		   catch (Exception e)
		   {
			   System.out.println("Could not execute SQL statement");
			   System.out.println(e.getMessage());
			   System.exit(1);
			  
		   }

		   // --- 4) process result set
		   try {
			   while (rset.next()) {
				   id = rset.getInt(0);
			   }     // --- end - while
		   }
		   catch (SQLException sqle) {
			   System.out.println("Could not process result set");
			   System.out.println(sqle.getMessage());
			   System.exit(1);
			  
		   }

		  
		   
	  

		
		
		
		return id;
	}
}
