package client.action;

import client.User;
import message.Message;
import response.Response;
import sendable.requests.PublishRequest;
import sendable.requests.Request;
import stream.Stream;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import static Tools.Tags.getTags;

public class PublishAction extends ClientAction{


    public PublishAction(Stream stream, String user) throws IOException {
        super(stream, user);
    }

    @Override
    public void doAction() throws IOException, ClassNotFoundException {
        System.out.println("Saisissez un message (pas plus de 255 caractères)");
        String body = reader.readLine();
        List<String> tags = getTags();
        Request request = new PublishRequest(userName,body,tags);
        stream.writeData(request);
        System.out.println("Requete envoyée : " + request);
        /*Response response = (Response) stream.getData();
        System.out.println(response);*/

    }
}
