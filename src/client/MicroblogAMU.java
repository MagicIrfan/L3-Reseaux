package client;

import Tools.Options;
import action.client.*;
import client.threads.DataReceiver;

import java.io.IOException;
import java.net.Socket;

/**
 * Classe représentant le client MicroblogAMU
 */
public class MicroblogAMU extends Client {

    /**
     * Permet de vérifier si le client est en marche
     */
    private boolean isRunning;
    /**
     * L'action du client
     */
    private ClientAction action;
    /**
     * Thread récupérant les données envoyées par le serveur
     */
    private final DataReceiver receiver;


    /**
     * Constructeur de MicroblogAMU
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public MicroblogAMU() throws IOException, ClassNotFoundException {
        super();
        this.isRunning = true;
        this.receiver = new DataReceiver(stream);
        new Thread(receiver).start();
        this.action = new ConnectAction(stream,userName);
        action.doAction();
        this.compute();
        Socket socket = this.getSocket();
        socket.close();
    }

    /**
     *
     * @param action
     */
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
                        setAction(new RcvIdsAction(stream));
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
                    case "F" ->{
                        setAction(new FamousUserAction(stream,userName));
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
    }

}
