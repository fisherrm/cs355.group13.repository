package edu.uwec.cs355.group13.service;

import org.restlet.Server;
import org.restlet.data.Protocol;

public class GeneratorUtilitiesServer {
	public static void main (String [] args) throws Exception {
		Server genUtilServer = new Server(Protocol.HTTP, 8111, GeneratorUtilitiesServerResource.class);
		genUtilServer.start();
	}
}
