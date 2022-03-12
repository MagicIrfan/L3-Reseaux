package server;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public abstract class Server  {

    protected ServerSocket serverSocket;
    protected ExecutorService executorService;

    public Server() throws IOException {
        this.serverSocket = new ServerSocket(12345);
        this.executorService = Executors.newCachedThreadPool();

    }

    public abstract void execute() throws IOException;
}
