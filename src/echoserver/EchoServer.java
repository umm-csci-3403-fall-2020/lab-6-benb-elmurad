package echoserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;

import jdk.javadoc.internal.doclets.toolkit.util.DocFinder.Output;

public class EchoServer {
	public static final int PORT_NUMBER = 6013;

	public static void main(String[] args) throws IOException, InterruptedException {
		EchoServer server = new EchoServer();
		server.start();
	}

	private void start() throws IOException, InterruptedException {
		ServerSocket serverSocket = new ServerSocket(PORT_NUMBER);
		while (true) {
			Socket socket = serverSocket.accept();
			InputStream inputStream = socket.getInputStream();
			OutputStream outputStream = socket.getOutputStream();
			connectionThread serverConnection = new connectionThread(socket, inputStream, outputStream);
			serverConnection.run();
		}
	}

	public class connectionThread extends Thread {
		public Thread connectThread;
		public Socket socketName;
		public InputStream inputStreamName;
		public OutputStream outputStreamName;

		connectionThread(Socket socket, InputStream inputStream, OutputStream outputStream) {
			socketName = socket;
			inputStreamName = inputStream;
			outputStreamName = outputStream;
		}

		public void run() {
			try {
				int line;
				while ((line = inputStream.read()) != -1) {
					outputStream.write(line);
					outputStream.flush();
				}

				socket.shutdownOutput();
				System.out.flush();
				socket.close();

			} catch (ConnectException ce) {
				System.out.prinln("We were unable to connect to the Client.");
			} catch (IOException ioe) {
				System.out.println("We caught an unexpected exception.");
				System.out.println(ioe);
			}
		}

	}
}
