/*
 * VendorPersistenceController - controller class to persist a Vendor
 * 
 * Created by Paul J. Wagner, 2/28/2013
 */
package service;

public class VendorPersistenceController {
	// data
	//private  Vendor Vendor;		// Vendor being worked with
	private DAOInterface dao;		// the Data Access Object (DAO) being used
	
	// methods
	// persistVendor - overall method to persist a single Vendor object
	public void persistVendor(Vendor Vendor) {
		System.out.println("PersistVendor() Started");
		String sqlStatement;		// SQL statement to persist the Vendor
		
			// could pass a Vendor object in as parameter to this method
		sqlStatement = generateInsertStmt(Vendor);
		dao.connect();
		dao.executeUpdate(sqlStatement);
		dao.disconnect();
		System.out.println("PersistVendor() Done");
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
	
	// generateInsertStmt - generate an SQL insert statement for a particular Vendor object
	public String generateInsertStmt(Vendor aVendor) {
		String result = null;
		
		// TODO: code to convert Vendor object to SQL insert statement string for that Vendor
		String vendorName = aVendor.getName();
	
		result = "INSERT INTO Vendor (VendorName) Values(\""+vendorName+"\")";
		return result;
	}

	
}
