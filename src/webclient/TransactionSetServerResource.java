package webclient;

import org.restlet.resource.ServerResource;

import service.TransactionSet;

public class TransactionSetServerResource extends ServerResource implements
		TransactionSetResource {
	// data
	private static TransactionSet tranSet = new TransactionSet();

	// methods
	public TransactionSetServerResource() {
		// TODO Auto-generated constructor stub
		System.out.println("TransactionSetServerResource constructor");
	}

	public TransactionSet retrieve() {
		System.out.println("transactions: "+ TransactionSetServerResource.tranSet.getTransactionSet());
		System.out.println("   startDate: "+ TransactionSetServerResource.tranSet.getStartDate());
		System.out.println("     endDate: "+ TransactionSetServerResource.tranSet.getEndDate());
		return TransactionSetServerResource.tranSet;
	}

	public void store(TransactionSet tranSet) {
		TransactionSetServerResource.tranSet = new TransactionSet(tranSet);
		System.out.println("transactions: "+ TransactionSetServerResource.tranSet.getTransactionSet());
		System.out.println("   startDate: "+ TransactionSetServerResource.tranSet.getStartDate());
		System.out.println("     endDate: "+ TransactionSetServerResource.tranSet.getEndDate());
	}
}
