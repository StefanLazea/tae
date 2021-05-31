package common;
import javax.activation.DataHandler;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public interface ServerInterface {
	String NAMESPACE = "https://axway.com";
	String NAME = "ServerService";
	
//	@WebMethod
//	String bookDate(String clientName, String startDate, String endDate, int days);
	
	@WebMethod
	@Oneway
	void transform(String mapper, DataHandler handler);

	@WebMethod
	@Oneway
	void estimate(String mapper, DataHandler handler, int days);
}

