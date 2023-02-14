package Main;

import Users.*;
import Messages.*;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    //----------Server data----------
    String name;
    Set<User> users;
    Set<Connection> connections;
    //----------Transmission data----------
    int port;

    public Server(String name, int port) {
        users = new HashSet<User>();
        connections = new HashSet<Connection>();

        this.name = name;
        this.port = port;
    }

    public void init() {
        try {
            ServerSocket serversocket = new ServerSocket(port);

            System.out.println("==========CHAT SERVER==========");
            System.out.println("Server =" + name + "= started");
            System.out.println("IP: " + InetAddress.getLocalHost().getHostAddress());
            System.out.println("Port: " + port);
            System.out.println("===============================");

            //Accept incoming connections
            while(true) {
                Socket socket = serversocket.accept();

                Connection connection = new Connection(socket, this);
                connections.add(connection);
                connection.start();
            }
        } catch(IOException ex) {
            System.out.println("IOException in Server.init");
        }
    }

    public void broadcast(TextMessage message) {
        Iterator<Connection> iterator = connections.iterator();
        while(iterator.hasNext()) {
            Connection connection = iterator.next();
            if(!connection.getUser().equals(message.getSender())) {
                connection.sendMessage(message);
            }
        }
    }

    //==========Mutators/Accessors==========
    public String getName() {
        return name;
    }
    public Set<User> getUsers() {
        return users;
    }
    //======================================

    //Main method
    //args - {name, port}
    public static void main(String[] args) {
        //Server server = new Server(args[0], Integer.parseInt(args[1]));
        Server server = new Server("JC", 1000);
        server.init();
    }
}
