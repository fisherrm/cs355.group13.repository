/*
 * StudentPersistenceController - controller class to persist a student
 * 
 * Created by Paul J. Wagner, 2/28/2013
 */
package edu.uwec.cs.wagnerpj.daointerface;

public class StudentPersistenceController {
	// data
	private Student student;		// student being worked with
	private DAOInterface dao;		// the Data Access Object (DAO) being used
	
	// methods
	// persistStudent - overall method to persist a single student object
	public void persistStudent() {
		String sqlStatement;		// SQL statement to persist the student
		
		student = new Student();	// could pass a student object in as parameter to this method
		sqlStatement = generateInsertStmt(student);
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
	
	// generateInsertStmt - generate an SQL insert statement for a particular student object
	public String generateInsertStmt(Student aStudent) {
		String result = null;
		// TODO: code to convert student object to SQL insert statement string for that student
		return result;
	}
}
