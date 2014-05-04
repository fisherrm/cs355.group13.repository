package edu.uwec.cs355.group13.service;

import org.restlet.resource.Get;
import org.restlet.resource.Put;

import edu.uwec.cs355.group13.common.GeneratorUtilities;
import edu.uwec.cs355.group13.common.RuleSet;


public interface GeneratorUtilitiesResource {
		@Get
		public RuleSet retrieve();

		@Put
		public void store(GeneratorUtilities genUtils);
}

