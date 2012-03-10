package com.cm.CMSource;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class Hello extends ServerResource{
	@Get
	public String represent(){
		String hello = "hello hello world hello class extend server resource hello cool";
		return hello;
	}
}
