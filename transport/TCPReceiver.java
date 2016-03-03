package transport;


import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import node.Node;
import wireformats.Event;
import wireformats.EventFactory;

/**
 * A class for TCP receiver. This class also implements runnable.
 * A thread can be spawned for receiving data. 
 *
 */

public class TCPReceiver implements Runnable{

	private Socket socket;
	private DataInputStream din;
	private Node node;
	
	public TCPReceiver(Socket socket, Node node) throws IOException {
		this.socket = socket;
		this.node = node;
		
		din = new DataInputStream(socket.getInputStream());
	}
	
	/**
	 * This method continuously reads the data from the socket.
	 * Once the data arrives, it calls Event Factory to get the Event object.
	 * The respective object is passed to the node it is intended for. 
	 */
	public void run() {
		int dataLength;
		while (socket != null) {
			try {
				dataLength = din.readInt();

				byte[] data = new byte[dataLength];
				din.readFully(data, 0, dataLength);
				Event e = EventFactory.getInstance().getEvent(data);
				node.handleEvent(e, socket);
			} 
			catch (SocketException se) {
				System.out.println(se.getMessage());
				break;
			} 
			catch (IOException ioe) {
				System.out.println(ioe.getMessage()) ;
				break;
			}

			
		}
	}
}
