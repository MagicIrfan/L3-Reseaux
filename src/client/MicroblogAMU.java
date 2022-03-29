package client;

import Tools.Options;
import client.action.*;
import threads.DataReceiver;

import java.io.IOException;
import java.net.Socket;
import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

public class MicroblogAMU extends Client {

    private boolean isRunning;
    private ClientAction action;
    private final DataReceiver receiver;

    public MicroblogAMU() throws IOException, ClassNotFoundException {
        super();
        this.isRunning = true;
        this.receiver = new DataReceiver(stream);
        new Thread(receiver).start();
        this.action = new ConnectAction(stream,userName);
        action.doAction();
    }

    public void setAction(ClientAction action){
        this.action = action;
    }



    public void compute(){
        try{
            String response;
            do {

                response = reader.readLine();
                switch (response) {
                    case "P" -> {
                        setAction(new PublishAction(stream, userName));
                        action.doAction();
                    }
                    case "S" -> {
                        setAction(new RepublishAction(stream, userName));
                        action.doAction();
                    }
                    case "I" -> {
                        setAction(new RcvIdsAction(stream, userName));
                        action.doAction();
                    }
                    case "R" -> {
                        setAction(new ReplyAction(stream, userName));
                        action.doAction();
                    }
                    case "M" -> {
                        setAction(new RcvMsgAction(stream, userName));
                        action.doAction();
                    }
                    case "A" -> {
                        setAction(new SubscribeAction(stream, userName));
                        action.doAction();
                    }
                    case "B" -> {
                        setAction(new UnSubscribeAction(stream, userName));
                        action.doAction();
                    }
                    case "C" ->{
                        setAction(new ShowMessagesAction(stream,userName));
                        action.doAction();
                    }
                    case "Q" -> isRunning = false;
                    default -> System.out.println(Options.getOptions());
                }
            }
            while (isRunning);

        } catch (IOException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }


    }

    public static void main(String [] args) throws IOException, ClassNotFoundException {
        Client amu = new MicroblogAMU();
        amu.compute();
        Socket socket = amu.getSocket();
        socket.close();
    }

}
