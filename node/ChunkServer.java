package node;

import transport.TCPReceiver;
import transport.TCPSender;
import transport.TCPServer;
import wireformats.ChunkServerSendsRegistration;
import wireformats.Event;
import wireformats.Protocol;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * Class for ChunkServer
 */
public class ChunkServer implements Node, Protocol {

    String serverIPAddress;
    TCPSender registrySender;
    TCPServer server;

    public ChunkServer(int port){
        server = new TCPServer(port, this);
        new Thread(server).start();
    }

    /**
     * This method establishes a connection between chunk server and controller
     * @param host : Controller IP
     * @param port : Controller port
     */
    public void client(String host, int port){
        Socket socket;
        TCPReceiver receiver;
        try {
            socket = new Socket(host, port);
        }
        catch(Exception e){
            System.out.println("Couldn't connect to server");
            return;
        }

        serverIPAddress = socket.getLocalAddress().getHostAddress();
        System.out.println("Connecting to registry at "+host+" "+port+" from "+socket.getLocalAddress().getHostAddress()+" "+socket.getLocalPort());

        try {
            receiver = new TCPReceiver(socket, this);
        }
        catch(Exception e){
            System.out.println("IO exception in TCP server");
            return;
        }
        new Thread(receiver).start();

        try {
            registrySender = new TCPSender(socket);
        }
        catch(Exception e){
            System.out.println("IO exception in TCP sender");
            return;
        }

        register(socket);

        Scanner keyboard = new Scanner(System.in);
        String input = keyboard.nextLine();

        while (input != null || !input.equalsIgnoreCase("quit")) {
            if(input.equals("exit")){
                //TODO: deregister(socket);
                System.out.println("TODO: Deregister chunk server");
            }
            input = keyboard.nextLine();
        }
        keyboard.close();
    }

    /**
     * This method sends the registration message to registry.
     * @param socket: Controller socket
     */
    public void register(Socket socket){
        byte[] registrationMessage;
        try {
            registrationMessage = new ChunkServerSendsRegistration(socket.getLocalAddress().getHostAddress(),
                    server.getPort()).getBytes();
            registrySender.sendData(registrationMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void handleEvent(Event e, Socket s) {

    }

    public static void main(String[] args){
        // TODO: Better way to parse command line arguments. Eg. using Apache commons library.
        if (args.length == 2) {
            ChunkServer chunkServer = new ChunkServer(0);
            chunkServer.client(args[0], Integer.parseInt(args[1]));
        }
        else{
            System.out.println("Incorrect arguments. Provide host address and port of Controller");
        }

    }
}
