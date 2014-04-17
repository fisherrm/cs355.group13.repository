package edu.uwec.cs.wagnerpj.restlettest;

import org.restlet.resource.ClientResource;

public class FirstClientResource extends ClientResource {
	public static void main (String [] args) throws Exception {
		/*
		// 1) Simple outputting the content of a Web page  
		new ClientResource("http://www.restlet.org").get().write(System.out); 
		*/
		
		// 2) More complex example with property setting
		// Create the client resource  
		ClientResource resource = new ClientResource("http://www.restlet.org");  
		 
		// Customize the referrer property  
		resource.setReferrerRef("http://www.mysite.org");  

		// Write the response entity on the console
		resource.get().write(System.out); 
		
	}
}
