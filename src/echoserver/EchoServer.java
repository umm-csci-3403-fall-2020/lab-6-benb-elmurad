package echoserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;

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
			connectionThread clientConnection = new connectionThread(socket,inputStream,outputStream);
	
			new Thread(clientConnection).start();
	}
   }
	public static class connectionThread implements Runnable {
		public Socket clientSocket = null;
		public InputStream inputStreamName = null;
		public OutputStream outputStreamName = null;

		connectionThread(Socket socket, InputStream inputStream, OutputStream outputStream) {
			clientSocket = socket;
			inputStreamName = inputStream;
			outputStreamName = outputStream;
		}

		public void run() {
			try {
				int line;
				while ((line = inputStreamName.read()) != -1) {
					outputStreamName.write(line);
					outputStreamName.flush();
				}

				clientSocket.shutdownOutput();
				System.out.flush();

			} catch (ConnectException ce) {
				System.out.println("We were unable to connect to the Client.");
			} catch (IOException ioe) {
				System.out.println("We caught an unexpected exception.");
				System.out.println(ioe);
			}
		}

	}

}

