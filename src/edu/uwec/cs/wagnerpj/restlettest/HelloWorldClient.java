package edu.uwec.cs.wagnerpj.restlettest;

import org.restlet.resource.ClientResource;

public class HelloWorldClient {
	public static void main (String [] args) throws Exception {
		ClientResource helloClientResource = new ClientResource("http://localhost:8111/");
		helloClientResource.get().write(System.out);
	}
}
