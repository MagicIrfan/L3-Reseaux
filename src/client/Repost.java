package client;

import Tools.ManyOptions;
import Tools.enterName;
import requests.*;
import response.Response;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Repost extends Client implements ManyOptions, enterName {


    private User user;
    private static boolean isRunning = false;

    public Repost() throws IOException {
        user = new User(getName());
    }

    public void republishMessage() throws IOException, ClassNotFoundException {
        System.out.println("Saisissez l'id du message à républier");
        String strSince = reader.readLine();
        long since = 0;
        if(!strSince.isEmpty()){
            if(!Float.isNaN(Float.parseFloat(strSince)))
                since = Long.parseLong(strSince);
        }

        Request request = new RepublishRequest(since,user.getName());
        stream.writeData(request);
        System.out.println("Requete envoyée : " + request);

        Response response =  (Response) stream.getData();
        System.out.println(response);
    }

    public List<String> getTags() throws IOException {
        List<String> tags = new ArrayList<>();
        String temp = "";

        do{
            String tag = "";
            System.out.println("Saisissez un tag (ne rien saisir si vous voulez finir la saisie)");
            System.out.print("#");
            temp = reader.readLine();
            if(!temp.isEmpty()){
                tag= "#" + temp;
                tags.add(tag);
            }

        }
        while(!temp.isEmpty());
        return tags;
    }

    public long processSince(String strSince){
        long since = 0;
        if(!strSince.isEmpty()){
            if(!Float.isNaN(Float.parseFloat(strSince)))
                since = Long.parseLong(strSince);
        }
        return since;
    }

    public void reply() throws IOException, ClassNotFoundException {
        System.out.println("Saisissez l'id du message à répondre");
        String strSince = reader.readLine();
        long since = processSince(strSince);
        System.out.println("Saisissez le message");
        String message = reader.readLine();

        List<String> tags = getTags();

        Request request = new ReplyRequest(since,message,user.getName(),tags);
        stream.writeData(request);
        System.out.println("Requete envoyée : " + request);

        Response response =  (Response) stream.getData();
        System.out.println(response);
    }


    @Override
    public void showOptions(){
        System.out.println("Options : ");
        System.out.println("R - Reposter un message");
        System.out.println("M - Republier un message");
        System.out.println("Q - Quitter");
    }


    public static void main(String [] args)throws IOException, ClassNotFoundException {
        Repost repost = new Repost();
        repost.compute();
        Socket socket = repost.stream.getSocket();
        socket.close();
    }

    @Override
    public void compute() {
        try{
            showOptions();
            char response;
            do {
                response = reader.readLine().charAt(0);
                switch (response) {
                    case 'R' -> {
                        reply();
                        isRunning = false;
                    }
                    case 'M' -> {
                        republishMessage();
                        isRunning = false;
                    }
                    case 'Q' -> isRunning = false;
                    default -> showOptions();
                }
            }
            while (response != 'Q' && isRunning);
        }
        catch(IOException | ClassNotFoundException exception){
            exception.printStackTrace();
        }

    }

    @Override
    public String getName() throws IOException {
        System.out.print("Saisissez votre pseudo : @");
        String name = "";
        do{
            name = reader.readLine();
        }
        while(name.isEmpty());

        return name;
    }
}
