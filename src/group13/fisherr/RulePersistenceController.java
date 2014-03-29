/*
 * RulePersistenceController - controller class to persist a Rule
 * 
 * Created by Paul J. Wagner, 2/28/2013
 */
package group13.fisherr;

public class RulePersistenceController {
	// data
	private  Rule rule;		// Rule being worked with
	private DAOInterface dao;		// the Data Access Object (DAO) being used
	
	// methods
	// persistRule - overall method to persist a single Rule object
	public void persistRule(Rule rule) {
		String sqlStatement;		// SQL statement to persist the Rule
		
		// could pass a Rule object in as parameter to this method
		sqlStatement = generateInsertStmt(rule);
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
	public String generateInsertStmt(Rule aRule) {
		String result = null;
		// TODO: code to convert Rule object to SQL insert statement string for that Rule
		String actualCL = ""+aRule.getActualConfidenceLevel();
		String generator_id = "1";
		String ruleSet_id = "";
		String antecedent = aRule.getAntecedent().toString();
		String consequent = aRule.getConsequent().toString();
		
		result = "INSERT INTO Rule (RuleActualConfidenceLevel, GeneratorUtilities_ID, RuleSet_ID, RuleAntecedent, RuleConsequent) "
				+ "Values("+actualCL+","+generator_id+","+ruleSet_id+","+antecedent+","+consequent+")";
		return result;
	}
}
