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
		try {
			ServerSocket serverSocket = new ServerSocket(PORT_NUMBER);
			while (true) {
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            Thread readInput = new Thread(() -> {
                try {
                    int line;
                    while ((line = inputStream.read()) != -1) {
                        outputStream.write(line);
							
                    }

                    socket.shutdownOutput();
                    System.out.flush();
                    socket.close(
			
		}
	}
}
                                
