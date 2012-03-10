package com.cm.CMSource.datain;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class DataDisplay extends ServerResource {
	@Get
	public String represent(){
		System.out.println("**** testing hellotest ****");
		return "Resource URI  : " + getReference() + '\n' + "Root URI      : " 
        + getRootRef() + '\n' + "Routed part   : " 
        + getReference().getBaseRef() + '\n' + "Remaining part: " 
        + getReference().getRemainingPart(); 
	}

}
