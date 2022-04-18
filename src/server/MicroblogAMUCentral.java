package server;

import client.User;
import message.Message;
import message.Notification;
import server.data.Database;
import stream.Stream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

//CLASSE REPRENSENTANT LE SERVEUR
public class MicroblogAMUCentral{

    private final ServerSocket serverSocket;
    private final ExecutorService executorService;
    private static final Database database = new Database();
    private static final ConcurrentHashMap<User,Stream> userStream = new ConcurrentHashMap<>();
    public static AtomicLong atomicID = new AtomicLong(0);

    public MicroblogAMUCentral() throws IOException {
        this.serverSocket = new ServerSocket(12345);
        this.serverSocket.setReuseAddress(true);
        this.executorService = Executors.newCachedThreadPool();

    }


    public ConcurrentHashMap<User,Stream> getUserStream(){
        return userStream;
    }

    public static void main(String[] arg) throws IOException {
        MicroblogAMUCentral server = new MicroblogAMUCentral();
        while(true){
            server.execute();
        }
    }

    public void execute() throws IOException {
        executorService.execute(new SocketHandler(serverSocket.accept(),database,this));
    }

}
