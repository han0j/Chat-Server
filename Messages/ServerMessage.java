package Messages;

import Users.*;

import java.util.*;

public class ServerMessage extends Message {
    String name;
    Set<User> users;

    public ServerMessage(String name, Set<User> users) {
        this.name = name;
        this.users = users;
    }

    //==========Mutators/Accessors==========
    public String getName() {
        return name;
    }
    public Set<User> getUsers() {
        return users;
    }
    //======================================
}
