package com.cm.CMSource;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class FirstStepsApplication extends Application {

	public synchronized Restlet createInboundRoot(){
		Router router = new Router(getContext());
		router.attach("/hello", Hello.class);
		return router;
	}
}
