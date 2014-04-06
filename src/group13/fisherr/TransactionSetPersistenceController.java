/*
 * TransactionPersistenceController - controller class to persist a Transaction
 * 
 * Created by Paul J. Wagner, 2/28/2013
 */
package group13.fisherr;

public class TransactionSetPersistenceController {
	// data
	private  TransactionSet transactionSset;		// Transaction being worked with
	private DAOInterface dao;		// the Data Access Object (DAO) being used
	
	// methods
	// persistTransaction - overall method to persist a single Transaction object
	public void persistTransactionSet(TransactionSet transactionSet) {
		String sqlStatement;		// SQL statement to persist the Transaction
		
			// could pass a Transaction object in as parameter to this method
		sqlStatement = generateInsertStmt(transactionSet);
		dao.connect();
		dao.executeUpdate(sqlStatement);
		dao.disconnect();
	}

	// setDAO - set the controller DAO to a given DAO
	public void setDAO(String daoLine) {
		if (daoLine.equals("Mock")) {
			dao = new MockDAO();
		}
		else if (daoLine.equals("MySQL")) {
			dao = new MySQLDAO();
		}
	}
	
	// generateInsertStmt - generate an SQL insert statement for a particular Transaction object
	public String generateInsertStmt(TransactionSet aTransactionSet) {
		String result = null;
		// TODO: code to convert Transaction object to SQL insert statement string for that Transaction
		// 1. Add real dates
		// 2. Change the file parsing of items.
		// 3. Handle Errors
		//String datetime = aTransactionSet.getDate();
		
		
		String startDate = "STR_TO_DATE(\""+aTransactionSet.getStartDate()+"\",\"%Y-%m-%d %H:%i:%S\")";
		String endDate = "STR_TO_DATE(\""+aTransactionSet.getEndDate()+"\",\"%Y-%m-%d %H:%i:%S\")";
		result = "INSERT INTO TransactionSet (TransactionSetStartDate, TransactionSetEndDate) Values("+startDate+","+endDate+")";
		return result;
	}
}
