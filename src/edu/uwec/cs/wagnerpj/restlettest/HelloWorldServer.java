package edu.uwec.cs.wagnerpj.restlettest;

import org.restlet.Server;
import org.restlet.data.Protocol;

public class HelloWorldServer {
	public static void main (String [] args) throws Exception {
		Server helloServer = new Server(Protocol.HTTP, 8111, HelloServerResource.class);
		helloServer.start();
	}
}
