package client.action;

import Tools.Tags;
import client.User;
import response.Response;
import sendable.requests.ReplyRequest;
import sendable.requests.Request;
import stream.Stream;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ReplyAction extends ClientAction{


    public ReplyAction(Stream stream, User user) throws IOException {
        super(stream, user);
    }

    public long processSince(String strSince){
        long since = 0;
        if(!strSince.isEmpty()){
            if(!Float.isNaN(Float.parseFloat(strSince)))
                since = Long.parseLong(strSince);
        }
        return since;
    }


    @Override
    public void doAction() throws IOException, ClassNotFoundException {
        System.out.println("Saisissez l'id du message à répondre");
        String strSince = reader.readLine();
        long since = processSince(strSince);
        System.out.println("Saisissez le message");
        String message = reader.readLine();

        List<String> tags = Tags.getTags();

        Request request = new ReplyRequest(since,message,user.getName(),tags);
        //stream.writeData(request);
        sendPacket(request);
        System.out.println("Requete envoyée : " + request);

        Response response =  (Response) stream.getData();
        System.out.println(response);
    }
}
