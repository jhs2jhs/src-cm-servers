package war.hellotest;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class HelloResource extends ServerResource {
	@Get
	public String represent(){
		System.out.println("**** testing hellotest ****");
		return "Hello cool server test";
	}

}
