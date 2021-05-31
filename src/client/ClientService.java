package client;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import javax.activation.DataHandler;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.ws.soap.Addressing;
import javax.xml.ws.soap.MTOM;

import common.ClientInterface;
import common.ServerInterface;



@WebService(
		serviceName = ClientService.NAME,
		targetNamespace = ClientService.NAMESPACE,
		endpointInterface = "common.ClientInterface"
)

@MTOM
@Addressing(required = true)
public class ClientService implements ClientInterface {

	public static final String FOLDER = "data/client/";
	
	@Override
	public void onTransform( @XmlMimeType("application/octet-stream") DataHandler handler) {
		try {
			String output = FOLDER + UUID.randomUUID().toString() + ".xml";
			handler.writeTo(new FileOutputStream(output));
			System.out.println("Result was saved to " + output);
			
		} catch (IOException e) {
			System.err.println("Error: "+ e.getMessage());
		}
	}

	@Override
	public void onError(String message) {
		System.err.println("Error: "+ message);
	}

	@Override
	public void onEstimate(@XmlMimeType("application/octet-stream") DataHandler handler) {
		// TODO Auto-generated method stub
		try {
			String output = FOLDER + "estimate" +  UUID.randomUUID().toString() + ".xml";
			handler.writeTo(new FileOutputStream(output));
			System.out.println("Result was saved to " + new FileOutputStream(output).toString());
			
		} catch (IOException e) {
			System.err.println("Error: "+ e.getMessage());
		}
		
	}

}
