package server;
import java.net.*;

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
import sendable.flux.ShowMsgFlux;
import sendable.flux.SubscribeFlux;
import sendable.flux.UnsubscribeFlux;
import server.data.Database;
import stream.Stream;

import java.io.*;

public class SocketHandler implements Runnable{

    private Socket socket;
    private Stream stream;
    private Database database;
    private MicroblogAMUCentral parent;

    public SocketHandler(Socket socket, Database database, MicroblogAMUCentral parent) throws IOException {
        this.socket = socket;
        this.stream = new Stream(socket);
        this.database = database;
        this.parent = parent;
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
                Response response = null;
                switch (strRequest) {
                    case "PUBLISH" -> {
                        long randomID = MicroblogAMUCentral.getNextId();
                        User newUser = database.getConnectedUser(user);
                        ProcessRequest process = new ProcessPublish(randomID, database,newUser);
                        response = process.getResponse(request);
                        if(response instanceof ConfirmationResponse && !database.getMessagesMap().get(newUser).isEmpty())
                            parent.sendMessagesToClient(user);
                        stream.writeData(response);
                    }
                    case "RCV_MSG" -> {
                        ProcessRequest processRequest = new ProcessRcvMsg(database);
                        response = processRequest.getResponse(request);
                        stream.writeData(response);

                    }
                    case "RCV_IDS" -> {
                        ProcessRequest processRequest = new ProcessRcvIds(database);
                        response = processRequest.getResponse(request);
                        stream.writeData(response);
                    }
                    case "REPLY" -> {
                        ProcessRequest processRequest = new ProcessReply(database, MicroblogAMUCentral.getNextId());
                        response = processRequest.getResponse(request);
                        User newUser = database.getConnectedUser(user);
                        if(response instanceof ConfirmationResponse && !database.getMessagesMap().get(newUser).isEmpty())
                            parent.sendMessagesToClient(user);
                        stream.writeData(response);
                    }
                    case "REPUBLISH" -> {
                        ProcessRequest processRequest = new ProcessRepublish(database);
                        response = processRequest.getResponse(request);
                        User newUser = database.getConnectedUser(user);
                        if(response instanceof ConfirmationResponse && !database.getMessagesMap().get(newUser).isEmpty())
                            parent.sendMessagesToClient(user);
                        stream.writeData(response);

                    }
                    case "CONNECT" -> {
                        Process process = new ProcessConnect(database,user);
                        response = process.getResponse(request);
                        if(response instanceof ConfirmationResponse)
                            parent.getUserStream().put(database.getConnectedUser(user),stream);
                        stream.writeData(response);

                    }
                    case "SUBSCRIBE" -> {
                        SubscribeFlux flux = (SubscribeFlux) request;
                        Socket userSocket = database.getSocketWithName(flux.getReceiver());
                        User user2 = new User(flux.getReceiver(),userSocket);
                        Process process = new ProcessSubscribe(database,user,user2);
                        response = process.getResponse(request);
                        stream.writeData(response);

                    }
                    case "UNSUBSCRIBE" -> {
                        UnsubscribeFlux flux = (UnsubscribeFlux) request;
                        Socket userSocket = database.getSocketWithName(flux.getReceiver());
                        User user2 = new User(flux.getReceiver(),userSocket);
                        Process process = new ProcessUnsubscribe(database,user, user2);
                        response = process.getResponse(request);
                        stream.writeData(response);

                    }
                    case "SHOW_MSG" -> {
                        ShowMsgFlux flux = (ShowMsgFlux) request;
                        Process process = new ProcessShowMsg(database,user);
                        response = process.getResponse(request);
                        stream.writeData(response);
                    }
                    default -> {}

                }
                System.out.println("Envoi de la réponse ...");
            }
        }
        catch(IOException | ClassNotFoundException | InterruptedException e)
        {
            e.printStackTrace();
        }

    }

}