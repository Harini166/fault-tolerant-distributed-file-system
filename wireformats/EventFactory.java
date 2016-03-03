package wireformats;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

/**
 *
 * This class has a singleton instance that can be accesed by getInstance() method.
 * This class returns an Event object for each data stream. 
 */

public class EventFactory implements Protocol{
	
	private static EventFactory instance = new EventFactory();
	
	private EventFactory(){}
	
	public static EventFactory getInstance(){
		return instance;
	}
	
	/**
	 * This method takes a byte stream as input. Based on the first integer in the stream, the method creates
	 * appropriate Object and returns it as an Event object.
	 * @param marshalledBytes - Byte packet
	 * @return Event
	 * @throws IOException
	 */
	
	public Event getEvent(byte[] marshalledBytes) throws IOException{
		ByteArrayInputStream baInputStream =new ByteArrayInputStream(marshalledBytes);
		DataInputStream din = new DataInputStream(new BufferedInputStream(baInputStream));
		
		int type = din.readInt();
		
		switch(type){
		case CHUNK_SERVER_SENDS_REGISTRATION: return new ChunkServerSendsRegistration(marshalledBytes);
		}
		return null;
	}
}
