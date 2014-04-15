/*
 * MySQLDAO - DAO class that persists to a MySQL database
 * 
 * Created by Paul J. Wagner, 2/28/2013
 */
package service;

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
import java.util.ArrayList;

public class MySQLDAO implements DAOInterface {
	// methods
	Connection conn = null;//connection object
	Statement stmt = null;// SQL statement object
	ResultSet rset = null;// statement result set object
	// --- set the username and password
	String user = "CS355GROUP13";		// bypass call to readEntry	
	String pass = "U856314$";
	ArrayList<String> errorList = new ArrayList<String>();
	
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
			  conn = DriverManager.getConnection("jdbc:mysql://dario.cs.uwec.edu/cs355group13",user,pass);
		   }
		   catch (SQLException sqle)
		   {
			  System.out.println ("Could not make connection to database");
			  System.out.println(sqle.getMessage());
			  System.exit(1);
		   }

		
		
		
		
		
	}

	public int executeUpdate(String query) {
		
		int resultCode = 0 ;
		System.out.println("MySQL DAO executeUpdate");
		// use connection to execute update,
		
		// set resutCode
		
		
		// --- 3) prepare and execute statement
		   
		   try
		   {
			  stmt = conn.createStatement();
			  resultCode = stmt.executeUpdate(query);
		   }
		   catch (Exception e)
		   {
			   System.out.println("Could not execute SQL INSERT statement");
			   System.out.println(e.getMessage());
			   //System.exit(1);
			   resultCode = -1;
			 
		   }

		   if(resultCode==-1){
			   System.out.println("Insert Failed");
			   errorList.add("Insert Failed");
		   }else{
			   System.out.println("Insert statement Succeeded");
		   }

		  
		   
	  

		
		
		
		return resultCode;
	}
	
	
	
	@Override
	//SELECT
	public int execute(String query) {
		int errorCode = 0;
		int id = 0;
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
			   System.out.println("Could not execute SQL SELECT statement");
			   System.out.println(e.getMessage());
			   //System.exit(1);
			   errorCode = -1;
		   }

		   // --- 4) process result set
		   
		   try {
			   if(errorCode ==0){
				   while (rset.next()) {
					   id = rset.getInt(1);
					 System.out.println("ID: " + id);
				   }     // --- end - while
			   }
		   }
		   catch (SQLException sqle) {
			   System.out.println("Could not process result set");
			   System.out.println(sqle.getMessage());
			   //System.exit(1);
			   errorCode= -1;
		   }

		  
		   
	  
		   if(errorCode == 0){
			   System.out.println("SELECT Succeeded");
			   return id;
		   }else{
			   System.out.println("SELECT Failed");
			   errorList.add("SELECT statement Failed");
		   }
		
		
		
		return errorCode;
	}
	
	
	
	public void disconnect() {
		// disconnect MySQL connection, statement, resultset
		
		
		 // --- 5) clean up statement and connection
		   try {
			   if(rset!=null){
				   //System.out.println("Successfully closed result Set");

				   rset.close();
			   }
			   if(stmt!=null){
				   
				   stmt.close();
				  // System.out.println("Successfully closed statement");
			   }
			   conn.close();
			   //System.out.println("Successfully closed connection");
		   } 
		   catch (SQLException sqle) {
			   System.out.println("Could not close statement and connection");
			   System.out.println(sqle.getMessage());
			   System.exit(1);
		   }
		   
		 

		
	}

	
}
