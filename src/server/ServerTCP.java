package server;
import java.net.*;
import java.io.*;
import java.util.concurrent.*;

public class ServerTCP {

    public static void main(String[] arg) throws IOException{
        ExecutorService executor = Executors.newCachedThreadPool();
        ServerSocket serverSocket = new ServerSocket(12345);
        while(true){
            executor.execute(new SocketHandler(serverSocket.accept()));
        }
    }
}