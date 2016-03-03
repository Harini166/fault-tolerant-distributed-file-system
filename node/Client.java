package node;

import wireformats.Event;
import wireformats.Protocol;

import java.net.Socket;

/**
 * Created by Prady on 3/2/16.
 */
public class Client implements Node, Protocol {
    @Override
    public void handleEvent(Event e, Socket s) {

    }
}
