package webclient;

import org.restlet.resource.ServerResource;

import service.GeneratorUtilities;

public class GeneratorUtilitiesServerResource extends ServerResource implements
		GeneratorUtilitiesResource {
	// data
	private static GeneratorUtilities genUtils = new GeneratorUtilities();

	// methods
	public GeneratorUtilitiesServerResource() {
		// TODO Auto-generated constructor stub
		System.out.println("GeneratorUtilitiesServerResource constructor");
	}

	public GeneratorUtilities retrieve() {
		System.out.println("GeneratorUtilities retrieve called");
		System.out.println("MSL: "+ GeneratorUtilitiesServerResource.genUtils.getMinimumSupportLevel());
		System.out.println("MCL: "+ GeneratorUtilitiesServerResource.genUtils.getMinimumConfidenceLevel());
		return GeneratorUtilitiesServerResource.genUtils;
	}

	public void store(GeneratorUtilities genUtils) {
		System.out.println("GeneratorUtilities store called");
		GeneratorUtilitiesServerResource.genUtils = new GeneratorUtilities(genUtils);
		System.out.println("MSL: "+ GeneratorUtilitiesServerResource.genUtils.getMinimumSupportLevel());
		System.out.println("MCL: "+ GeneratorUtilitiesServerResource.genUtils.getMinimumConfidenceLevel());
	
	}
}
