package node;

import transport.TCPServer;
import wireformats.Event;
import wireformats.Protocol;

import java.net.Socket;

/**
 * Created by Prady on 3/2/16.
 */
public class Controller implements Node, Protocol {
    @Override
    public void handleEvent(Event e, Socket s) {

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