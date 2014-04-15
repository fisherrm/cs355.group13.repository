/*
 * DAOInterface - interface for various DAO implementations
 * 
 * Created by Paul J. Wagner, 2/28/2013
 */
package service;



public interface DAOInterface {
	public void connect();				// connect to data store
	public int execute(String query);	// execute a query, return error code
	public int executeUpdate(String query);	// execute a query, return result code
	public void disconnect();			// disconnect from data store
}
