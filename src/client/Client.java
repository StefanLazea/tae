package client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.xml.namespace.QName;
import javax.xml.ws.Endpoint;
import javax.xml.ws.EndpointReference;
import javax.xml.ws.Service;
import javax.xml.ws.soap.AddressingFeature;
import com.sun.xml.internal.ws.api.addressing.OneWayFeature;
import com.sun.xml.internal.ws.api.addressing.WSEndpointReference;
import common.ServerInterface;
import common.Settings;

public class Client {
	public static void main(String[] args) {
		try {
			EndpointReference reference = Endpoint.publish(Settings.CLIENT_ENDPOINT, new ClientService()).getEndpointReference();
			ServerInterface proxy = Service.create(
					new URL(Settings.SERVER_ENDPOINT + "?wsdl"),
					new QName(ServerInterface.NAMESPACE, ServerInterface.NAME))
					.getPort(ServerInterface.class, 
							new AddressingFeature(), 
							new OneWayFeature(true, new WSEndpointReference(reference)));
			System.out.println("Enter a transformation name and input file or 'exit'");
			
			try(Scanner scanner = new Scanner(System.in)) {
				while(true) {
					if(scanner.hasNextLine()) {
						String command = scanner.nextLine();
						if("exit".equalsIgnoreCase(command)) {
							System.exit(0);
						} else {
							String [] arguments = command.split("\\s+");
							System.out.println("aici" +arguments[0]);
							if(arguments[0] == "estimat") {
								proxy.estimate(arguments[0], new DataHandler(new FileDataSource(ClientService.FOLDER + "reservations.xml")), 2);								
							}
							if(arguments.length == 2) {
								proxy.transform(arguments[0], new DataHandler(new FileDataSource(ClientService.FOLDER + arguments[1])));
							}
						}
					}
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
