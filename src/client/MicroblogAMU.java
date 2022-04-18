package client;

import Tools.Options;
import action.client.*;

import java.io.IOException;
import java.net.Socket;
//CLASSE REPRESENTANT LE CLIENT MICROBLOGAMU
public class MicroblogAMU extends Client {

    private boolean isRunning;
    private ClientAction action;


    public MicroblogAMU() throws IOException, ClassNotFoundException {
        super();
        this.isRunning = true;
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
                System.out.println(Options.getOptions());
                response = reader.readLine();
                switch (response) {
                    case "P" -> {
                        setAction(new PublishAction(stream, userName));
                        action.doAction();
                        isRunning = false;
                    }
                    case "S" -> {
                        setAction(new RepublishAction(stream, userName));
                        action.doAction();
                        isRunning = false;
                    }
                    case "I" -> {
                        setAction(new RcvIdsAction(stream));
                        action.doAction();
                        isRunning = false;
                    }
                    case "R" -> {
                        setAction(new ReplyAction(stream, userName));
                        action.doAction();
                        isRunning = false;
                    }
                    case "M" -> {
                        setAction(new RcvMsgAction(stream, userName));
                        action.doAction();
                        isRunning = false;
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
                        isRunning = false;
                    }
                    case "F" ->{
                        setAction(new FamousUserAction(stream,userName));
                        action.doAction();
                        isRunning = false;
                    }
                    case "E" ->{
                        setAction(new MostRepublishedMsgAction(stream,userName));
                        action.doAction();
                        isRunning = false;
                    }
                    case "Q" -> {
                        setAction(new DisconnectAction(stream,userName));
                        action.doAction();
                        isRunning = false;
                    }
                    default -> System.out.println(Options.getOptions());
                }
            }
            while (isRunning);

        } catch (IOException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }


    }

    public static void main(String [] args) throws IOException, ClassNotFoundException, InterruptedException {
        Client amu = new MicroblogAMU();
        amu.compute();
        amu.getSocket().close();
    }

}
