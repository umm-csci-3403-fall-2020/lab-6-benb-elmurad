package echoserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class EchoClient {
	public static final int PORT_NUMBER = 6013;

	public static void main(String[] args) throws IOException {
		EchoClient client = new EchoClient();
		client.start();
	}

	private void start() throws IOException {
		Socket socket = new Socket("localhost", PORT_NUMBER);
		InputStream socketInputStream = socket.getInputStream();
		OutputStream socketOutputStream = socket.getOutputStream();
		
		Thread clientRead = new Thread (){
			try {

		int echoThisByte;
		
		// read from System.in and send that info to the server 1 byte at a time.
		while ((echoThisByte = System.in.read()) != 1) {
			// write the byte to the outputstream
			socketOutputStream.write(echoThisByte);
			// send it to the server
			socketOutputStream.flush();
		}
		socket.shutdownOutput();
		System.out.flush();
		// Put your code here.
	}
	catch (ConnectException ce) {
		System.out.println("We were unable to connect to the host");
		System.out.println("You should make sure the server is running.");
	} catch (IOException ioe) {
		System.out.println("We caught an unexpected exception");
		System.err.println(ioe);
	
}
		};
