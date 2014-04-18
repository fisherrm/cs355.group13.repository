package service;

import java.io.Serializable;

public class Vendor implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;

	 public Vendor(){
		 this.name="";
	 }
	
	public Vendor(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	} 

}
