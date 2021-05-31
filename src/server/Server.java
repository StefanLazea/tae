package server;

import java.util.Scanner;

import javax.xml.ws.Endpoint;

import common.Settings;

public class Server {

	public static void main(String[] args) {
		Endpoint.publish(Settings.SERVER_ENDPOINT, new ServerService());
		System.out.println(String.format("Server is listening on %s, type 'exit' to close it.",
				Settings.SERVER_ENDPOINT));
		
		try (Scanner scanner = new Scanner(System.in)) {
			while(true) {
				if(scanner.hasNextLine() && "exit".equalsIgnoreCase(scanner.nextLine())) {
					System.exit(0);
				}
			}
		}
	}

}
