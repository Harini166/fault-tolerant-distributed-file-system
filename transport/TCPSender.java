package transport;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
/**
 * A class for sending data over established TCP socket.
 *
 */
public class TCPSender {
	
	private DataOutputStream dout;
	
	public TCPSender(Socket socket) throws IOException {
		dout = new DataOutputStream(socket.getOutputStream());
	}
	
	/**
	 * This method accepts a byte array of data. It first sends the length of the byte array followed by the actual byte array.
	 * @param dataToSend : Byte array of data to send
	 * @throws IOException
	 */
	public synchronized void sendData(byte[] dataToSend) throws IOException {
		int dataLength = dataToSend.length;
		dout.writeInt(dataLength);
		dout.write(dataToSend, 0, dataLength);
		dout.flush();
	}

}
