package transport;

import java.net.Socket;

/**
 * This class stores the local host and port and remote host and port of an established socket.
 * In addition it creates a TCPSender and holds its reference.
 *
 */

public class TCPConnection {
	
	private Socket socket;
	private TCPSender sender;
	private String localIPAddress;
	private int localPort;
	private String remoteIPAddress;
	private int remotePort;
	
	public TCPConnection(Socket socket, TCPSender sender){
		this.socket = socket;
		this.sender = sender;
		this.localIPAddress = socket.getLocalAddress().getHostAddress();
		this.localPort = socket.getLocalPort();
	}

	public Socket getSocket() {
		return socket;
	}

	public void setRemoteIPAddress(String remoteIPAddress) {
		this.remoteIPAddress = remoteIPAddress;
	}

	public void setRemotePort(int remotePort) {
		this.remotePort = remotePort;
	}

	public TCPSender getSender() {
		return sender;
	}

	public String getLocalIPAddress() {
		return localIPAddress;
	}

	public int getLocalPort() {
		return localPort;
	}

	public String getRemoteIPAddress() {
		return remoteIPAddress;
	}

	public int getRemotePort() {
		return remotePort;
	}

	public String toString(){
		return "IP: "+remoteIPAddress+" Port: "+remotePort;
	}

}


