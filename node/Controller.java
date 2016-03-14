package node;

import transport.TCPServer;
import wireformats.Event;
import wireformats.Protocol;

import java.net.Socket;

/**
 * Class for Controller
 */

public class Controller implements Node, Protocol {

    @Override
    public void handleEvent(Event event, Socket socket) {
        int type = event.getType();
        switch(type){
            case CHUNK_SERVER_SENDS_REGISTRATION: registerChunkServer(event, socket);
                break;
        }

    }

    /**
     * Method to handle registration request from chunk server
     * @param event : ChunkServerSendsRegistration object
     * @param socket : Socket at which event arrived
     */
    public void registerChunkServer(Event event, Socket socket){
        System.out.println("Registration request from "+event.toString());
    }

    public static void main (String [] args) {
        // TODO: Better way to parse command line arguments. Eg. using Apache commons library.
        int port = 0;
        if (args.length == 1) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("Invalid input: Port " + args[0] + " must be an integer.");
            }
        } else {
            System.err.println("Invalid input: Please provide a port number.");
            System.exit(0);
        }

        Controller controller = new Controller();
        TCPServer server = new TCPServer(port, controller);
        new Thread(server).start();
    }
}