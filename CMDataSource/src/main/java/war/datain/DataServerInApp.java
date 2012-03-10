package war.datain;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class DataServerInApp extends Application{
	
	@Override
	public synchronized Restlet createInboundRoot(){
		Router router = new Router(getContext());
		router.attach("/init", DBInit.class);
		//router.attach("/display", DataDisplay.class);
		return router;
	}

}
