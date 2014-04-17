package edu.uwec.cs.wagnerpj.restlettwowayserial;

import org.restlet.Server;
import org.restlet.data.Protocol;

public class ContactServer {
	public static void main (String [] args) throws Exception {
		Server contactServer = new Server(Protocol.HTTP, 8111, ContactServerResource.class);
		contactServer.start();
	}
}
