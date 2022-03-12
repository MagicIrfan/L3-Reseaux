package server;
import java.net.*;

import client.User;
import packet.Packet;
import process.flux.ProcessConnect;
import process.Process;
import process.flux.ProcessSubscribe;
import process.flux.ProcessUnsubscribe;
import process.request.*;
import sendable.Sendable;
import response.*;
import sendable.flux.SubscribeFlux;
import stream.Stream;

import java.io.*;
import java.util.*;

public class SocketHandler extends Handler{

    public SocketHandler(Socket socket){
        super(socket);
    }

    @Override
    public void run(){
        try
        {
            Stream stream = new Stream(socket);
            Packet packet = (Packet) stream.getData();
            User user = packet.getUser();
            Sendable request = packet.getSendable();
            String strRequest = request.getName();
            Response response = null;
            switch(strRequest){
                case "PUBLISH" -> {
                    long randomID = getNextId();
                    ProcessRequest process = new ProcessPublish(randomID,database);
                    response = process.getResponse(request);
                }
                case "RCV_MSG" ->{
                    ProcessRequest processRequest = new ProcessRcvMsg(database);
                    response = processRequest.getResponse(request);

                }
                case "RCV_IDS" ->{
                    ProcessRequest processRequest = new ProcessRcvIds(database);
                    response = processRequest.getResponse(request);
                }
                case "REPLY" ->{
                    ProcessRequest processRequest = new ProcessReply(database,getNextId());
                    response = processRequest.getResponse(request);
                }
                case "REPUBLISH" ->{
                    ProcessRequest processRequest = new ProcessRepublish(database);
                    response = processRequest.getResponse(request);
                }
                case "CONNECT" ->{
                    Process process = new ProcessConnect(subscribers,user);
                    response = process.getResponse(request);

                }
                case "SUBSCRIBE" ->{
                    SubscribeFlux flux = (SubscribeFlux) request;
                    User user2 = new User(flux.getName());
                    Process process = new ProcessSubscribe(subscribers,user,user2);
                    response = process.getResponse(request);

                }
                case "UNSUBSCRIBE" ->{
                    SubscribeFlux flux = (SubscribeFlux) request;
                    User user2 = new User(flux.getName());
                    Process process = new ProcessUnsubscribe(subscribers,user,user2);
                    response = process.getResponse(request);

                }
                default -> {}

            }
            stream.writeData(response);

            System.out.println("Envoi de la réponse ...");
        }
        catch(IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }

    }

}