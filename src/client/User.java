package client;

public class User {
    private String name;
    private boolean isConnected;

    public User(String name, boolean isConnected){
        this.name = name;
        this.isConnected = isConnected;
    }

    public void connect(){
        isConnected = true;
    }

    public void disconnect(){
        isConnected = false;
    }
}
