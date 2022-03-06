package client;

import Tools.enterName;
import message.Message;
import requests.PublishRequest;
import requests.Request;
import response.Response;

import java.io.*;
import java.net.*;
import java.util.*;

public class Publisher extends Client implements enterName {

    private User user;

    public Publisher() throws IOException {
        user = new User(getName());
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

    public void sendMessage() throws IOException, ClassNotFoundException {
        System.out.println("Saisissez un message (pas plus de 255 caractères)");
        String body = reader.readLine();
        List<String> tags = getTags();
        Request request = new PublishRequest(user.getName(),body,tags);
        stream.writeData(request);
        System.out.println("Requete envoyée : " + request);
        Response response =  (Response) stream.getData();
        System.out.println(response);
    }



    public static void main(String [] args) throws IOException{
        Publisher publisher = new Publisher();
        publisher.compute();
        Socket socket = publisher.stream.getSocket();
        socket.close();
    }

    @Override
    public void compute() {
        try{
            sendMessage();
        }
        catch (IOException | ClassNotFoundException exception){
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
