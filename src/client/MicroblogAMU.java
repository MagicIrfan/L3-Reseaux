package client;

import Tools.ManyOptions;
import Tools.Name;
import client.action.*;
import java.io.IOException;
import java.net.Socket;

public class MicroblogAMU extends Client implements ManyOptions{

    private static boolean isRunning =true;
    private User user;
    private ClientAction action;

    public MicroblogAMU() throws IOException, ClassNotFoundException {
        super();
        this.user = new User(Name.getName());
        this.action = new ConnectAction(stream,user);
        action.doAction();
    }

    public void setAction(ClientAction action){
        this.action = action;
    }


    @Override
    public void showOptions() {
        System.out.println("Options : ");
        System.out.println("P - Publier un message");
        System.out.println("I - Recevoir les id de message");
        System.out.println("M - Recevoir un message");
        System.out.println("R - Répondre à un message");
        System.out.println("S - Republier un message");
        System.out.println("A - S'abonner à un utilisateur");
        System.out.println("B - Se désabonner à un utilisateur");
        System.out.println("Q - Quitter");
    }


    public void compute() {
        try{
            showOptions();
            char response;
            do{
                response = reader.readLine().charAt(0);
                switch(response){
                    case 'P'->{
                        setAction(new PublishAction(stream,user));
                        action.doAction();
                        isRunning = false;
                    }
                    case 'S' ->{
                        setAction(new RepublishAction(stream,user));
                        action.doAction();
                        isRunning = false;
                    }
                    case 'I'->{
                        setAction(new RcvIdsAction(stream,user));
                        action.doAction();
                        isRunning = false;
                    }
                    case 'R'->{
                        setAction(new ReplyAction(stream,user));
                        action.doAction();
                        isRunning = false;
                    }
                    case 'M' ->{
                        setAction(new RcvMsgAction(stream,user));
                        action.doAction();
                        isRunning = false;
                    }
                    case 'A' ->{
                        setAction(new SubscribeAction(stream,user));
                        action.doAction();
                    }
                    case 'B' ->{
                        setAction(new UnSubscribeAction(stream,user));
                        action.doAction();
                    }
                    case 'Q' -> isRunning = false;
                    default->showOptions();
                }
            }
            while(response != 'Q' && isRunning);
        }
        catch(IOException | ClassNotFoundException exception){
            exception.printStackTrace();
        }

    }

    public static void main(String [] args) throws IOException, ClassNotFoundException {
        Client amu = new MicroblogAMU();
        amu.compute();
        Socket socket = amu.stream.getSocket();
        socket.close();
    }



}
