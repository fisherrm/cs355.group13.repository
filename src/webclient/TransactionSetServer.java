package webclient;

import org.restlet.Server;
import org.restlet.data.Protocol;

public class TransactionSetServer {
	public static void main (String [] args) throws Exception {
		Server transactionSetServer = new Server(Protocol.HTTP, 8111, TransactionSetServerResource.class);
		transactionSetServer.start();
	}
}
