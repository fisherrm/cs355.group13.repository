package group13.fisherr;

public class GeneratorUtilitiesPersistenceController {

		// data
		//private  GeneratorUtilities generatorUtilities;		// Rule being worked with
		private DAOInterface dao;		// the Data Access Object (DAO) being used
		
		// methods
		// persistRule - overall method to persist a single Rule object
		public void persistGeneratorUtilities(GeneratorUtilities GeneratorUtilities) {
			String sqlStatement;		// SQL statement to persist the Rule
			
			// could pass a Rule object in as parameter to this method
			sqlStatement = generateInsertStmt(GeneratorUtilities);
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
		
		// generateInsertStmt - generate an SQL insert statement for a particular Rule object
		public String generateInsertStmt(GeneratorUtilities aGeneratorUtilities) {
			String result = null;
			// TODO: code to convert Rule object to SQL insert statement string for that Rule
			
			double minSupportLevel = aGeneratorUtilities.getMinimumSupportLevel();
			double minConfidenceLevel = aGeneratorUtilities.getMinimumConfidenceLevel();
			String queryTranSetID = "SELECT MAX(TransactionSet_ID) FROM TransactionSet";
			
			dao.connect();
			int transactionSetID = dao.execute(queryTranSetID);
			dao.disconnect();
			
			result = "INSERT INTO GeneratorUtilities (GeneratorUtilitiesMinimumSupportLevel, GeneratorUtilitiesMinimumConfidenceLevel, TransactionSet_ID)Values("+minSupportLevel+","+minConfidenceLevel+", "+transactionSetID+")";

			return result;
		}
	}
