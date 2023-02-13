package Messages;

import Users.*;

public class TextMessage extends Message {
    String text;
    User sender;

    //==========Mutators/Accessors==========
    public String getText() {
        return text;
    }
    public User getSender() {
        return sender;
    }
    //======================================
}
