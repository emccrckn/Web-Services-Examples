package endpoints;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface RandServiceInterface {
	
	@WebMethod
	public int next1();
	
	@WebMethod
	public int[] nextN(final int n);
}
