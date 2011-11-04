package war;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class Hello extends ServerResource{
	@Get
	public String represent(){
		return "hello world hello class extend server resource";
	}
}
