/*
 * RulePersistenceController - controller class to persist a Rule
 * 
 * Created by Paul J. Wagner, 2/28/2013
 */
package group13.fisherr;

public class RuleSetPersistenceController {
	// data
	private  RuleSet ruleset;		// Rule being worked with
	private DAOInterface dao;		// the Data Access Object (DAO) being used
	
	// methods
	// persistRule - overall method to persist a single Rule object
	public void persistRule(RuleSet ruleset) {
		String sqlStatement;		// SQL statement to persist the Rule
		
		// could pass a Rule object in as parameter to this method
		sqlStatement = generateInsertStmt(ruleset);
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
	
	// generateInsertStmt - generate an SQL insert statement for a particular Rule object
	public String generateInsertStmt(RuleSet aRuleSet) {
		String result = null;
		// TODO: code to convert Rule object to SQL insert statement string for that Rule
		
		String datetime = aRuleSet.getDate();
		String generator_id = "1";
		result = "INSERT INTO RuleSet (RuleSetDateTime, GeneratorUtilities_ID) Values("+datetime+","+generator_id+")";

		return result;
	}
}
