package edu.uwec.cs.wagnerpj.restlettwowayserial;

import org.restlet.resource.ClientResource;

public class ContactClient {
	public static void main (String [] args) throws Exception {

		Address address = new Address("123 S. Main", "", "54701", "Eau Claire", "USA");
		Contact contact = new Contact("Zaphod", "Beeblebrox", address, 20);

		Contact newContact = null;
		
		ClientResource clientResource = new ClientResource("http://localhost:8111/");
        
		ContactResource proxy = clientResource.wrap(ContactResource.class);

		proxy.store(contact);
		newContact = proxy.retrieve();

		if (newContact != null) {
            System.out.println("firstname: " + newContact.getFirstName());
            System.out.println(" lastname: " + newContact.getLastName());
            System.out.println("      age: " + newContact.getAge());
            System.out.println("     city: " + newContact.getHomeAddress().getCity());
		}
		else {
			System.out.println("got null contact");
		}
	}
}
