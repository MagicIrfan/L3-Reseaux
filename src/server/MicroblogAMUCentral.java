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

public class MicroblogAMUCentral{

    private final ServerSocket serverSocket;
    private final ExecutorService executorService;
    private static final Database database = new Database();
    private static final ConcurrentHashMap<User,Stream> userStream = new ConcurrentHashMap<>();
    public static AtomicLong atomicID = new AtomicLong(0);

    public void sendMessagesToClient(User user) throws IOException {

        User connectedUser = database.getConnectedUser(user);
        ConcurrentHashMap<User, Deque<Message>> messages = database.getMessagesMap();
        ConcurrentSkipListMap<User,Deque<User>> listUsers = database.getSubscribers();
        Deque<User> subscribers = listUsers.get(connectedUser);
        boolean sent = false;

        for(Map.Entry<User,Deque<User>> entry : listUsers.entrySet()){
            User connectedSubscriber = database.getConnectedUser(entry.getKey());
            Deque<User> oui = entry.getValue();
            if(oui.contains(connectedUser)){
                Stream stream1 = userStream.get(connectedSubscriber);
                stream1.writeData(new Notification(connectedUser.getName()));
            }
        }

        if(sent)
            messages.put(connectedUser,new ConcurrentLinkedDeque<>());

    }

    public MicroblogAMUCentral() throws IOException {
        this.serverSocket = new ServerSocket(12345);
        this.serverSocket.setReuseAddress(true);
        this.executorService = Executors.newCachedThreadPool();
        while(true){
            this.execute();
        }
    }


    public ConcurrentHashMap<User,Stream> getUserStream(){
        return userStream;
    }

    public static void main(String[] arg) throws IOException {
        MicroblogAMUCentral server = new MicroblogAMUCentral();
    }

    public void execute() throws IOException {
        executorService.execute(new SocketHandler(serverSocket.accept(),database,this));
    }

}
