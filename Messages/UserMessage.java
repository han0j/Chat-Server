package Messages;

import Users.*;

public class UserMessage extends Message {
    User user;

    public UserMessage(User user) {
        this.user = user;
    }

    //==========Mutators/Accessors==========
    public User getUser() {
        return user;
    }
    //======================================
}
