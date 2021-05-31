package common;

import javax.activation.DataHandler;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public interface ClientInterface {
	String NAMESPACE = "https://axway.com";
	String NAME = "ServerService";
	
	@WebMethod
	@Oneway
	void onTransform(DataHandler handler);
	
	@WebMethod
	@Oneway
	void onEstimate(DataHandler handler);
	
	@WebMethod
	@Oneway
	void onError(String message);
}
