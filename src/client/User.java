package client;

import java.io.Serializable;
import java.net.Socket;

public class User implements Serializable,Comparable<User> {
    private String name;
    private Socket socket;

    public User(String name, Socket socket){
        this.name = name;
        this.socket = socket;
    }

    public String getName(){
        return name;
    }

    @Override
    public String toString(){
        return name + " " + socket.getPort();
    }

    public Socket getSocket(){
        return socket;
    }

    @Override
    public int compareTo(User o) {
        if(name.equals(o.getName()))
            return 0;

        if(name.compareTo(o.getName()) < 0)
            return -1;

        return 1;
    }
}
