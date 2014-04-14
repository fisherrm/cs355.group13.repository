/*
 * RulePersistenceController - controller class to persist a Rule
 * 
 * Created by Paul J. Wagner, 2/28/2013
 */
package group13.fisherr;



public class RulePersistenceController {
	// data
	//private  Rule rule;		// Rule being worked with
	private DAOInterface dao;		// the Data Access Object (DAO) being used
	
	// methods
	// persistRule - overall method to persist a single Rule object
	public void persistRule(Rule rule) {
		String sqlStatement;		// SQL statement to persist the Rule
		
		// could pass a Rule object in as parameter to this method
		sqlStatement = generateInsertStmt(rule);
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
	public String generateInsertStmt(Rule aRule) {
		String result = null;
		// TODO: code to convert Rule object to SQL insert statement string for that Rule
		String query = "SELECT MAX(RuleSet_ID) FROM RuleSet";
		
		double actualCL = aRule.getActualConfidenceLevel();
		String antecedent = aRule.getAntecedent().toString();
		String consequent = aRule.getConsequent().toString();
		dao.connect();
		int ruleSetID = dao.execute(query);
		dao.disconnect();
		result = "INSERT INTO Rule (RuleAntecedent, RuleConsequent, RuleActualConfidenceLevel, RuleSet_ID) "
				+ "Values(\""+antecedent+"\",\""+consequent+"\", "+actualCL+", "+ruleSetID+")";
		return result;
	}
	
	
	
}
