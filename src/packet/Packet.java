package packet;

import client.User;
import sendable.Sendable;

import java.io.Serializable;

public class Packet implements Serializable {
    private User user;
    private Sendable sendable;

    public Packet(User user,Sendable sendable){
        this.user = user;
        this.sendable = sendable;
    }

    public User getUser(){
        return user;
    }

    public Sendable getSendable(){
        return sendable;
    }



}
