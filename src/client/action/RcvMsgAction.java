package client.action;

import client.User;
import response.Response;
import sendable.requests.RcvMsgRequest;
import sendable.requests.Request;
import stream.Stream;

import java.io.IOException;
import java.net.Socket;

public class RcvMsgAction extends ClientAction{


    public RcvMsgAction(Stream stream, User user) throws IOException {
        super(stream, user);
    }

    @Override
    public void doAction() throws IOException, ClassNotFoundException {
        System.out.println("Saisir l'id du message");
        long id = Long.parseLong(reader.readLine());
        Request request = new RcvMsgRequest(id);
        //stream.writeData(request);
        sendPacket(request);
        System.out.println("Requete envoyée : " + request);

        Response response =  (Response) stream.getData();
        System.out.println(response);
    }
}
