package server;
import java.net.*;

import message.Message;
import process.*;
import requests.*;
import response.*;
import stream.Stream;

import java.io.*;
import java.util.*;

public class SocketHandler implements Runnable{

    private final Socket socket;
    private static final Database database = new Database();
    private static long id = 0;

    public SocketHandler(Socket socket){
        this.socket = socket;
    }

    public long getNextId(){
        id+=1;
        return id;
    }


    @Override
    public void run(){
        try
        {
            Stream stream = new Stream(socket);
            Request request = (Request) stream.getData();
            String strRequest = request.getName().toString();
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