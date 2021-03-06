package echoserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Socket;

public class EchoClient {
	public static final int PORT_NUMBER = 6013;

	public static void main(String[] args) throws IOException {
		EchoClient client = new EchoClient();
		// public String server;
		// if (args.length == 0) {
		// server = "127.0.0.1";
		// } else {
		// server = args[0];
		// }
		client.start();

	}

	public void start() throws IOException {
		Socket socket = new Socket("localhost", PORT_NUMBER);
		InputStream socketInputStream = socket.getInputStream();
		OutputStream socketOutputStream = socket.getOutputStream();

		// create our thread that sends keyboard input to the server
		GetOutgoingThread clientSend = new GetOutgoingThread(socket, socketOutputStream);
		new Thread(clientSend).start();
		// create our thread that receives that input echoed back from the server
		GetIncomingThread clientReceive = new GetIncomingThread(socket, socketInputStream);
		new Thread(clientReceive).start();
		
		// closing all the stuff we opened
		//socket.close();
		//socketInputStream.close();
		//socketOutputStream.close();
	}

	public class GetOutgoingThread extends Thread {
		public Socket socketName;
		public OutputStream outputStreamName;
		public InputStream inputStreamName;

		GetOutgoingThread(Socket socket, OutputStream outputStream) {
			socketName = socket;
			outputStreamName = outputStream;
			// System.out.println("Creating " + threadName);
		}

		public void run() {
			try {
				int echoThisByte;
				while ((echoThisByte = System.in.read()) != -1) {
					// write the byte to the outputstream
					outputStreamName.write(echoThisByte);
					// send it off to the server
					outputStreamName.flush();
				}
				// tell the server we are done sending it stuff
				socketName.shutdownOutput();
			} catch (ConnectException ce) {
				System.out.println("We were unable to connect to localhost.");
			} catch (IOException ioe) {
				System.out.println("We caught an unexpected exception");
				System.out.println(ioe);
			}
		}
	}
	public class GetIncomingThread extends Thread {
		public Socket socketName;
		public InputStream inputStreamName;

		GetIncomingThread(Socket socket, InputStream inputStream){
			socketName = socket;
			inputStreamName = inputStream;
		}

		public void run() {
			try {
				int echoThisByte;
				while ((echoThisByte = inputStreamName.read()) != -1) {
					// write the byte we just read to the System.out
					System.out.write(echoThisByte);
					// make sure we actually push what was written to System.out
					System.out.flush();
				}
				socketName.shutdownInput();
			} catch (ConnectException ce) {
				System.out.println("We were unable to connect to localhost.");
			} catch (IOException ioe) {
				System.out.println("We caught an unexpected exception");
				System.out.println(ioe);
			}
				
		}
	}

}
