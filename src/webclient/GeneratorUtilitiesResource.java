package webclient;

import org.restlet.resource.Get;
import org.restlet.resource.Put;

import service.GeneratorUtilities;


public interface GeneratorUtilitiesResource {
		@Get
		public GeneratorUtilities retrieve();

		@Put
		public void store(GeneratorUtilities genUtils);
}

