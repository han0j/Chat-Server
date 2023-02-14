package Users;

import Main.*;
import Users.*;
import Messages.*;
import org.w3c.dom.Text;

import java.io.*;
import java.net.*;
import java.util.*;

public class Connection extends Thread {
    //----------Connection data----------
    Server server;
    User user;
    //----------Transmission data----------
    ObjectInputStream ojinput;
    ObjectOutputStream ojoutput;

    //====================CONSTRUCTOR====================
    public Connection(Socket socket, Server server) {
        try {
            InputStream input = socket.getInputStream();
            ojinput = new ObjectInputStream(input);
            OutputStream output = socket.getOutputStream();
            ojoutput = new ObjectOutputStream(output);

            this.server = server;
        } catch(IOException ex) {
            System.out.println("IOException in Connection.Constructor");
        }
    }
    //===================================================

    public void run() {
        try {
            UserMessage usermessage = (UserMessage) ojinput.readObject();
            setUser(usermessage.getUser());
            System.out.println("User |" + usermessage.getUser().getUsername() + "| joined");

            ojoutput.writeObject(new ServerMessage(server.getName(), server.getUsers()));

            //Listen for text messages from user
            while(true) {
                TextMessage message = (TextMessage) ojinput.readObject();
                server.broadcast(message);
            }
        } catch(IOException ex) {
            System.out.println("IOException in Connection.run");
        } catch(ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException in Connection.run");
        }
    }

    public void sendMessage(Message message) {
        try {
            ojoutput.writeObject(message);
        } catch(IOException ex) {
            System.out.println("IOException in Connection.sendMessage");
        }
    }

    //==========Mutators/Accessors==========
    public void setUser(User user) {
        this.user = user;
    }
    public User getUser() {
        return user;
    }
    //======================================
}
