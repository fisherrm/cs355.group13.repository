package edu.uwec.cs.wagnerpj.restlettest;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

/**
 * Resource which has only one representation.
 */
public class HelloServerResource extends ServerResource {

    @Get
    public String represent() {
        return "hello, world";
    }

}

