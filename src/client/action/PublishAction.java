package client.action;

import client.User;
import response.Response;
import sendable.requests.PublishRequest;
import sendable.requests.Request;
import stream.Stream;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import static Tools.Tags.getTags;

public class PublishAction extends ClientAction{


    public PublishAction(Stream stream, User user) throws IOException {
        super(stream, user);
    }

    @Override
    public void doAction() throws IOException, ClassNotFoundException {
        System.out.println("Saisissez un message (pas plus de 255 caractères)");
        String body = reader.readLine();
        List<String> tags = getTags();
        Request request = new PublishRequest(user.getName(),body,tags);
        //stream.writeData(request);
        sendPacket(request);
        System.out.println("Requete envoyée : " + request);
        Response response =  (Response) stream.getData();
        System.out.println(response);
    }
}
