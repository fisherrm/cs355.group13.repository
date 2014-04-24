package service;

import org.restlet.resource.Get;
import org.restlet.resource.Put;

import common.GeneratorUtilities;
import common.RuleSet;


public interface GeneratorUtilitiesResource {
		@Get
		public RuleSet retrieve();

		@Put
		public void store(GeneratorUtilities genUtils);
}

