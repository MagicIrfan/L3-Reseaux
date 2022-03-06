package client;

public class User {
    private String name;
    private boolean isConnected;

    public User(String name){
        this.name = "@"+name;
    }

    public void connect(){
        isConnected = true;
    }

    public void disconnect(){
        isConnected = false;
    }

    public String getName(){
        return name;
    }
}
