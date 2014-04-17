package edu.uwec.cs.wagnerpj.restlettwowayserial;

import org.restlet.resource.ServerResource;

public class ContactServerResource extends ServerResource
									implements ContactResource {
	// data
	private static Contact contact = new Contact();
	
	// methods
	public ContactServerResource () {
		System.out.println("ContactServerResource constructor");
	}

	public Contact retrieve() {
		ContactServerResource.contact.setFirstName("Raul");
		System.out.println("set contact first name to " + ContactServerResource.contact.getFirstName());
		return ContactServerResource.contact; 
	}
	
	public void store (Contact contact) {
		ContactServerResource.contact = new Contact(contact);
		System.out.println("First name: " + ContactServerResource.contact.getFirstName());
		System.out.println("City      : " + ContactServerResource.contact.getHomeAddress().getCity());
	}
}
