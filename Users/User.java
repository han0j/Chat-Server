package Users;

import java.io.Serializable;

public class User implements Serializable {
    //User data
    String username;

    public User(String username) {
        this.username = username;
    }

    //==========Mutators/Accessors==========
    public String getUsername() {
        return username;
    }
    //======================================
}
