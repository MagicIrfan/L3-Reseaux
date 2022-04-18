package server;
import java.net.*;

import action.server.*;
import client.User;
import sendable.Sendable;
import server.data.Database;
import stream.Stream;

import java.io.*;

//PROGRAMME REPRESENTANT LES ACTIONS DU SERVEUR
public class SocketHandler implements Runnable{

    private Socket socket;
    private Stream stream;
    private Database database;
    private MicroblogAMUCentral parent;
    private ServerAction action;
    private boolean isRunning;

    public SocketHandler(Socket socket, Database database, MicroblogAMUCentral parent) throws IOException {
        this.socket = socket;
        this.stream = new Stream(socket);
        this.database = database;
        this.parent = parent;
        this.isRunning = true;
    }

    public void setAction(ServerAction action){
        this.action = action;
    }

    @Override
    public void run(){
        try
        {
            while(isRunning) {
                Sendable request = (Sendable) stream.getData();
                String strRequest = request.getName();
                String userName = request.getSender();
                User user = new User(userName,socket);
                switch (strRequest) {
                    case "PUBLISH" -> {
                        setAction(new PublishServerAction(database,request,user,parent,stream));
                        action.doAction();
                        isRunning = false;
                    }
                    case "RCV_MSG" -> {
                        setAction(new RcvMsgServerAction(database,request,stream));
                        action.doAction();
                        isRunning = false;
                    }
                    case "RCV_IDS" -> {
                        setAction(new RcvIdsServerAction(database,request,stream));
                        action.doAction();
                        isRunning = false;
                    }
                    case "REPLY" -> {
                        setAction(new ReplyServerAction(database,request,stream,user,parent));
                        action.doAction();
                        isRunning = false;
                    }
                    case "REPUBLISH" -> {
                        setAction(new RepublishServerAction(database,request,stream,user,parent));
                        action.doAction();
                        isRunning = false;

                    }
                    case "CONNECT" -> {
                        setAction(new ConnectServerAction(database,request,user,parent.getUserStream(),stream));
                        action.doAction();

                    }
                    case "SUBSCRIBE" -> {
                        setAction(new SubscribeServerAction(database,request,stream,user));
                        action.doAction();

                    }
                    case "UNSUBSCRIBE" -> {
                        setAction(new UnSubscribeServerAction(database,request,stream,user));
                        action.doAction();

                    }
                    case "SHOW_MSG" -> {
                        setAction(new ShowMessagesServerAction(database,request,stream,user));
                        action.doAction();
                        isRunning = false;
                    }
                    case "FAMOUSNAME" -> {
                        setAction(new FamousUserServerAction(database,request,stream));
                        action.doAction();
                        isRunning = false;
                    }
                    case "MOST_REPUBLISHED_MSG" -> {
                        setAction(new MostRepublishedMsgAction(database,request,stream));
                        action.doAction();
                        isRunning = false;
                    }
                    case "DISCONNECT" ->{
                        setAction(new DisconnectServerAction(database,request,stream,user));
                        action.doAction();
                        isRunning = false;
                    }
                    default -> {}

                }
                System.out.println("Envoi de la réponse ");
            }
        }
        catch(IOException | ClassNotFoundException | InterruptedException e)
        {
            e.printStackTrace();
        }

    }

}