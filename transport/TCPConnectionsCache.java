package transport;

import java.util.HashMap;

/**
 * A cache for storing TCP connection for each node in the overlay.
 *
 */
public class TCPConnectionsCache {

	HashMap<Integer, TCPConnection> cache;
	
	public TCPConnectionsCache(){
		cache = new HashMap<Integer, TCPConnection>();
		
	}
	
	public void addConnection(int nodeID,TCPConnection connection){
		cache.put(nodeID, connection);
	}
	
	public TCPConnection getConection(int nodeID){
		return cache.get(nodeID);
	}
	
}
