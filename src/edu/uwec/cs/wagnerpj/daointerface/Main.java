/*
 * Main - driver to test DAO interface, MySQL and mock DAO classes
 * 
 * Created by Paul J. Wagner, 2/28/2013
 */
package edu.uwec.cs.wagnerpj.daointerface;

import java.io.*;

public class Main {
	public static void main (String [] args) {
		StudentPersistenceController spc = new StudentPersistenceController();		// controller for delegating student persistence

		String daoString = null;
	    InputStreamReader unbuffered = new InputStreamReader( System.in );
	    BufferedReader keyboard = new BufferedReader( unbuffered );
		try {
			System.out.println("Use (Mock) DAO or (MySQL) DAO? Mock");
			daoString = keyboard.readLine();
		}
		catch (IOException error) {
			System.err.println("Error reading input");
		}

		spc.setDAO(daoString);
		spc.persistStudent();
	}
}
