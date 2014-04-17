package webclient;

import org.restlet.resource.Get;
import org.restlet.resource.Put;

import service.TransactionSet;


public interface TransactionSetResource {
		@Get
		public TransactionSet retrieve();

		@Put
		public void store(TransactionSet tranSet);
}

