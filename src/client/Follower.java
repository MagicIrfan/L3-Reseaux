package client;

import Tools.CharVerificator;
import Tools.ManyOptions;
import Tools.enterName;
import requests.PublishRequest;
import requests.RcvIdsRequest;
import requests.RcvMsgRequest;
import requests.Request;
import response.Response;
import stream.Stream;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Follower extends Client implements ManyOptions, enterName {

    private static boolean isRunning =true;

    public Follower() throws IOException {
        super();
    }

    public void receiveIds() throws IOException, ClassNotFoundException {
        System.out.println("Saisissez l'auteur des messages : (ne rien saisir sinon)");
        String author = getName();

        System.out.println("Saisir un tag (ne rien saisir sinon)");
        String temp = reader.readLine();

        System.out.println("Saisir l'id du message où les messages ont été publiés après cet id");
        String strSince = reader.readLine();

        System.out.println("Nombre maximum d'identifiants renvoyés : ");
        String strLimit = reader.readLine();

        long since = 0,limit = Long.MAX_VALUE;

        if(!author.isEmpty()){
            if(!CharVerificator.nameIsValid(author))
                author = null;
        }
        if(!temp.isEmpty()){
            if(!CharVerificator.tagIsValid(temp))
                temp = null;
        }
        if(!strSince.isEmpty()){
            if(!Float.isNaN(Float.parseFloat(strSince))){
                long tempsince = Long.parseLong(strSince);
                since = tempsince < 0 ? since : tempsince;
            }

        }

        if(!strLimit.isEmpty()){
            if(!Float.isNaN(Float.parseFloat(strLimit))){
                long templimit = Long.parseLong(strLimit);
                limit = templimit < 0 ? limit : templimit;
            }
        }


        Request request = new RcvIdsRequest(author,temp,since,limit);
        stream.writeData(request);
        System.out.println("Requete envoyée : " + request);

        Response response =  (Response) stream.getData();
        System.out.println(response);

    }

    public void receiveMsg() throws IOException, ClassNotFoundException{

        System.out.println("Saisir l'id du message");
        long id = Long.parseLong(reader.readLine());
        Request request = new RcvMsgRequest(id);
        stream.writeData(request);
        System.out.println("Requete envoyée : " + request);

        Response response =  (Response) stream.getData();
        System.out.println(response);
    }

    @Override
    public void showOptions(){
        System.out.println("Options : ");
        System.out.println("I - Recevoir les id de message");
        System.out.println("M - Recevoir un message");
        System.out.println("Q - Quitter");
    }

    public static void main(String [] args)throws IOException, ClassNotFoundException  {
        Follower follower = new Follower();
        follower.compute();
        Socket socket = follower.stream.getSocket();
        socket.close();
    }

    @Override
    public void compute() {
        try{
            showOptions();
            char response;
            do{
                response = reader.readLine().charAt(0);
                switch(response){
                    case 'I'->{
                        receiveIds();
                        isRunning = false;
                    }
                    case 'M' ->{
                        receiveMsg();
                        isRunning = false;
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

    @Override
    public String getName() throws IOException {
        return reader.readLine();
    }
}
