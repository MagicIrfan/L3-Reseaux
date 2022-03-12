package client.action;

import client.User;
import response.Response;
import sendable.Sendable;
import sendable.flux.ConnectFlux;
import stream.Stream;

import java.io.IOException;
import java.net.Socket;

public class ConnectAction extends ClientAction {


    public ConnectAction(Stream stream, User user) throws IOException {
        super(stream, user);
    }

    @Override
    public void doAction() throws IOException, ClassNotFoundException {
        Sendable sendable = new ConnectFlux(user.getName());
        sendPacket(sendable);
        System.out.println("Requête envoyée : " + sendable);
        Response response =  (Response) stream.getData();
        System.out.println(response);
    }
}
