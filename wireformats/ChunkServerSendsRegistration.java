package wireformats;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * A class for messaging nodes to register at Registry. 
 *
 */

public class ChunkServerSendsRegistration implements Event, Protocol{

	private int type = CHUNK_SERVER_SENDS_REGISTRATION;
	private String ipAddress;
	private int portNumber;
	
	public ChunkServerSendsRegistration(String ipAddress, int portNumber){
		this.ipAddress = ipAddress;
		this.portNumber = portNumber;
	}
	
	public ChunkServerSendsRegistration(byte[] marshalledBytes) throws IOException {
		ByteArrayInputStream baInputStream =new ByteArrayInputStream(marshalledBytes);
		DataInputStream din = new DataInputStream(new BufferedInputStream(baInputStream));
		
		type = din.readInt();
		int identifierLength = din.readInt();
		byte[] identifierBytes = new byte[identifierLength];
		din.readFully(identifierBytes);
		ipAddress = new String(identifierBytes);

		portNumber = din.readInt();
		baInputStream.close();
		din.close();
	}
	

	public byte[] getBytes() throws IOException {
		byte[] marshalledBytes;
		ByteArrayOutputStream baOutputStream = new ByteArrayOutputStream();
		DataOutputStream dout = new DataOutputStream(new BufferedOutputStream(baOutputStream));
		
		dout.writeInt(type);
		byte[] identifierBytes = ipAddress.getBytes();
		int elementLength = identifierBytes.length;
		dout.writeInt(elementLength);
		dout.write(identifierBytes);

		dout.writeInt(portNumber);

		dout.flush();
		marshalledBytes = baOutputStream.toByteArray();
		baOutputStream.close();
		dout.close();
		return marshalledBytes;
	}

	@Override
	public int getType() {
		return type;
	}
	
	public String getIpAddress() {
		return ipAddress;
	}

	public int getPortNumber() {
		return portNumber;
	}
}
