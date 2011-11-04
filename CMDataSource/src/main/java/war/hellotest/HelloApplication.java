package war.hellotest;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class HelloApplication extends Application{
	
	@Override
	public synchronized Restlet createInboundRoot(){
		Router router = new Router(getContext());
		router.attach("/test", HelloResource.class);
		router.attach("/json", HelloJSON.class);
		router.attach("/sqlite", HelloSQLite.class);
		//router.attach("/mysql", HelloMysql.class);
		return router;
	}

}
