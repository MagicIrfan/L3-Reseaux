package server;
import java.net.*;

import action.server.*;
import client.User;;
import message.Message;
import process.flux.ProcessConnect;
import process.Process;
import process.flux.ProcessShowMsg;
import process.flux.ProcessSubscribe;
import process.flux.ProcessUnsubscribe;
import process.request.*;
import sendable.Sendable;
import response.*;
import sendable.flux.ConnectFlux;
import sendable.flux.ShowMsgFlux;
import sendable.flux.SubscribeFlux;
import sendable.flux.UnsubscribeFlux;
import sendable.requests.RepublishRequest;
import server.data.Database;
import stream.Stream;

import java.io.*;

public class SocketHandler implements Runnable{

    private Socket socket;
    private Stream stream;
    private Database database;
    private MicroblogAMUCentral parent;
    private ServerAction action;

    public SocketHandler(Socket socket, Database database, MicroblogAMUCentral parent) throws IOException {
        this.socket = socket;
        this.stream = new Stream(socket);
        this.database = database;
        this.parent = parent;
    }

    public void setAction(ServerAction action){
        this.action = action;
    }

    @Override
    public void run(){
        try
        {
            while(true) {
                Sendable request = (Sendable) stream.getData();
                String strRequest = request.getName();
                String userName = request.getSender();
                User user = new User(userName,socket);
                switch (strRequest) {
                    case "PUBLISH" -> {
                        setAction(new PublishServerAction(database,request,user,parent,stream));
                        action.doAction();
                    }
                    case "RCV_MSG" -> {
                        setAction(new RcvMsgServerAction(database,request,stream));
                        action.doAction();

                    }
                    case "RCV_IDS" -> {
                        setAction(new RcvIdsServerAction(database,request,stream));
                        action.doAction();
                    }
                    case "REPLY" -> {
                        setAction(new ReplyServerAction(database,request,stream,user,parent));
                        action.doAction();
                    }
                    case "REPUBLISH" -> {
                        setAction(new RepublishServerAction(database,request,stream,user,parent));
                        action.doAction();

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
                    }
                    case "FAMOUSNAME" -> {
                        setAction(new FamousUserServerAction(database,request,stream));
                        action.doAction();
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