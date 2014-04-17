package edu.uwec.cs.wagnerpj.restlettwoway;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

/**
 * Resource which has only one representation.
 */
public class HelloServerResource extends ServerResource {

    @Get
    public String represent() {
    	String name = getQuery().getValues("name");
        return ("hello world, " + name);
    }

}

