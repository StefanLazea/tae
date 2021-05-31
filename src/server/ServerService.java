package server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.activation.DataHandler;
import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.namespace.QName;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.soap.Addressing;
import javax.xml.ws.soap.MTOM;
import javax.xml.ws.wsaddressing.W3CEndpointReference;
import javax.xml.ws.wsaddressing.W3CEndpointReferenceBuilder;

import com.sun.xml.internal.ws.api.addressing.WSEndpointReference;
import com.sun.xml.internal.ws.addressing.WsaPropertyBag;

import common.ClientInterface;
import common.ServerInterface;

@WebService(
		serviceName = ServerInterface.NAME,
		targetNamespace = ServerInterface.NAMESPACE,
		endpointInterface = "common.ServerInterface"
)

@MTOM
@Addressing(required = true)
@SuppressWarnings("restriction")
public class ServerService implements ServerInterface{
	private static final String FOLDER = "data/server/";
	
	@Resource
	private WebServiceContext context;
	
	@Override
	public void transform(String mapper, @XmlMimeType("application/octet-stream") DataHandler handler) {
		ClientInterface proxy = getClientEndpointReference().getPort(ClientInterface.class);
		try (ByteArrayOutputStream stream = new ByteArrayOutputStream()) {
			handler.writeTo(stream);
			String data = DocumentTransformer.transform(FOLDER + mapper + ".xsl", new String(stream.toByteArray()));
			proxy.onTransform(new DataHandler(data, "text/xml"));
		} catch (Exception e) {
			proxy.onError(e.getMessage());
		}
	}
	
	private W3CEndpointReference getClientEndpointReference() {
		WSEndpointReference reference = (WSEndpointReference) context.getMessageContext().get(WsaPropertyBag.WSA_REPLYTO_FROM_REQUEST); 
		return new W3CEndpointReferenceBuilder()
				.serviceName(new QName(ClientInterface.NAMESPACE, ClientInterface.NAME))
				.address(reference.getAddress())
				.wsdlDocumentLocation(reference.getAddress() + "?wsdl")
				.build();
	}

	@Override
	public void estimate(String mapper, DataHandler handler, int days) {
		// TODO Auto-generated method stub
		ClientInterface proxy = getClientEndpointReference().getPort(ClientInterface.class);
		try (ByteArrayOutputStream stream = new ByteArrayOutputStream()) {
			
		} catch (Exception e) {
			proxy.onError(e.getMessage());
		}
	}

	
//	@Override
//	public String bookDate(String clientName, String startDate, String endDate, int days) {
//		Reservation reservation = new Reservation(startDate, endDate, clientName, days);
//		return reservation.sendReservationInfo();
//	}
	
}
