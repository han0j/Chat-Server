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
        Server server = new Server(args[0], Integer.parseInt(args[1]));
        server.init();
    }
}
