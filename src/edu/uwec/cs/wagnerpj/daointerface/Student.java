/*
 * Student - entity class for one student
 * 
 * Created by Paul J. Wagner, 2/28/2013
 */
package edu.uwec.cs.wagnerpj.daointerface;

public class Student {
	// data
	private int sID;		// student ID number
	private String sName;	// student name
	private double sGPA;	// student grade point average
	
	// methods
	// constructors
	// all-arg constructor
	public Student(int someID, String someName, double someGPA) {
		sID = someID;
		sName = someName;
		sGPA = someGPA;
	}
	
	// default constructor
	public Student() {
		this(0, new String("Ima Student"), 3.0);
	}
	
	// other methods
	// TODO: implement getters and setters if needed
	// TODO: implement other standard methods: toString, compareTo, equals, hashCode
}
