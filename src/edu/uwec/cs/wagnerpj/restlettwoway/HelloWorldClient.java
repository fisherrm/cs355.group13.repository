package edu.uwec.cs.wagnerpj.restlettwoway;

import org.restlet.resource.ClientResource;

public class HelloWorldClient {
	public static void main (String [] args) throws Exception {
		ClientResource helloClientResource = new ClientResource("http://localhost:8111/?name=Paul");
		helloClientResource.get().write(System.out);
	}
}
