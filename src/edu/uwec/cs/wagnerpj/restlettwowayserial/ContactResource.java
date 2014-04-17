package edu.uwec.cs.wagnerpj.restlettwowayserial;

import org.restlet.resource.Get;
import org.restlet.resource.Put;

public interface ContactResource {
	@Get
	public Contact retrieve();

	@Put
	public void store(Contact contact);
}
