package client.action;

import client.User;
import response.Response;
import sendable.requests.RepublishRequest;
import sendable.requests.Request;
import stream.Stream;

import java.io.IOException;
import java.net.Socket;

public class RepublishAction extends ClientAction{


    public RepublishAction(Stream stream, User user) throws IOException {
        super(stream, user);
    }

    @Override
    public void doAction() throws IOException, ClassNotFoundException {
        System.out.println("Saisissez l'id du message à républier");
        String strSince = reader.readLine();
        long since = 0;
        if(!strSince.isEmpty()){
            if(!Float.isNaN(Float.parseFloat(strSince)))
                since = Long.parseLong(strSince);
        }

        Request request = new RepublishRequest(since,user.getName());
        //stream.writeData(request);
        sendPacket(request);
        System.out.println("Requete envoyée : " + request);

        Response response =  (Response) stream.getData();
        System.out.println(response);
    }
}