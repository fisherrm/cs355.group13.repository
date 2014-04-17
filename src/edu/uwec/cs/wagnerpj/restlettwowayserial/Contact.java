package edu.uwec.cs.wagnerpj.restlettwowayserial;

import java.io.Serializable;

public class Contact implements Serializable {

	private static final long serialVersionUID = 1L;

	private int age;

	private String firstName;

	private Address homeAddress;

	private String lastName;

	public Contact() {
	}

	public Contact(String firstName, String lastName, Address homeAddress,
			int age) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.homeAddress = homeAddress;
		this.age = age;
	}
	
	public Contact(Contact aContact) {
		Address address = new Address();
		address.setLine1(aContact.getHomeAddress().getLine1());
		address.setLine2(aContact.getHomeAddress().getLine2());
		address.setZipCode(aContact.getHomeAddress().getZipCode());
		address.setCity(aContact.getHomeAddress().getCity());
		address.setCountry(aContact.getHomeAddress().getCountry());
		setFirstName(aContact.getFirstName());
		setLastName(aContact.getLastName());
		setHomeAddress(address);
		setAge(aContact.getAge());		
	}

	public int getAge() {
		return age;
	}

	public String getFirstName() {
		return firstName;
	}

	public Address getHomeAddress() {
		return homeAddress;
	}

	public String getLastName() {
		return lastName;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
