package webclient;
import java.util.ArrayList;

import org.restlet.resource.ClientResource;

import service.Transaction;
import service.TransactionSet;
import edu.uwec.cs.wagnerpj.restlettwowayserial.Address;
import edu.uwec.cs.wagnerpj.restlettwowayserial.Contact;
import edu.uwec.cs.wagnerpj.restlettwowayserial.ContactResource;

public class TransactionSetClient {
	
		public static void main (String [] args) throws Exception {

			//should we make constructor take in the dates?
			//need to make it get actual transactionSets
			TransactionSet tranSet = new TransactionSet(new ArrayList<Transaction>());

			TransactionSet newTranSet = null;
			
			ClientResource clientResource = new ClientResource("http://localhost:8111/");
	        
			TransactionSetResource proxy = clientResource.wrap(TransactionSetResource.class);

			proxy.store(tranSet);
			newTranSet = proxy.retrieve();

			if (newTranSet != null) {
	            System.out.println("transactions: " + newTranSet.getTransactionSet());
	            System.out.println("   startDate: " + newTranSet.getStartDate());
	            System.out.println("     endDate: " + newTranSet.getEndDate());

	            
			}
			else {
				System.out.println("got null transactionSet");
			}
		}
	
}
