package transport;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import node.Node;

/**
 * A class for TCP Server.
 * This class implements Runnable. So, server can be started in a separate thread.
 *
 */

public class TCPServer implements Runnable {
	
	private int port;
	private Node node;
	
	public TCPServer(int port, Node node){
        this.port = port;
        this.node = node;
    }
	
	/**
	 * Thread's run method. The server continuously accepts incoming connections.
	 * A receiver thread is spawned for each incoming connection.
	 */
	@SuppressWarnings("resource")
	public void run() {
		
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(port);
			System.out.println("Started server at port "+serverSocket.getLocalPort());
			port = serverSocket.getLocalPort();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		while (true) {
			Socket socket = null;
			try {
				socket = serverSocket.accept();
			} catch (IOException e) {
				e.printStackTrace();
			}
			catch (Exception e){
                e.printStackTrace();
            }
			try {
				TCPReceiver receiver = new TCPReceiver(socket, node);
				new Thread(receiver).start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public int getPort(){
		return port;
	}


	
	
}
