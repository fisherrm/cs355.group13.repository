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
	public void persistTransaction(TransactionSet transactionSet) {
		String sqlStatement;		// SQL statement to persist the Transaction
		
			// could pass a Transaction object in as parameter to this method
		sqlStatement = generateInsertStmt(transactionSet);
		dao.connect();
		dao.execute(sqlStatement);
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
		
		String datetime = aTransactionSet.getDate();
		String generator_id = "1";
		result = "INSERT INTO TransactionSet (TransactionSetDateTime, GeneratorUtilities_ID) Values("+datetime+","+generator_id+")";
		return result;
	}
}
