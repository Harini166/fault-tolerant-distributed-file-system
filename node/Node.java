package node;

import java.net.Socket;

import wireformats.Event;
/**
 * An interface for both MessagingNode and Registry. 
 * This interface is used by TCP Receiver so that it can call handleEvent method for both Registry and Messaging Node.
 *
 */
public interface Node {
	void handleEvent(Event e, Socket s);
}
