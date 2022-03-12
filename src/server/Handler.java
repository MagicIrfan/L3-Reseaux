package server;

import client.User;

import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public abstract class Handler implements Runnable  {
    protected Socket socket;
    protected static Database database;
    protected static long id = 0;
    protected static ConcurrentHashMap<User, List<User>> subscribers;

    public Handler(Socket socket){
        this.socket = socket;
        database = new Database();
        subscribers = new ConcurrentHashMap<>();
    }

    protected long getNextId(){
        id+=1;
        return id;
    }

    @Override
    abstract public void run();
}
