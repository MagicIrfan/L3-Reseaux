package client.action;

import client.User;
import response.Response;
import sendable.Sendable;
import sendable.flux.ConnectFlux;
import stream.Stream;

import java.io.IOException;
import java.net.Socket;

public class ConnectAction extends ClientAction {


    public ConnectAction(Stream stream, String userName) throws IOException {
        super(stream, userName);
    }

    @Override
    public void doAction() throws IOException, ClassNotFoundException {
        Sendable sendable = new ConnectFlux(userName);
        stream.writeData(sendable);
        System.out.println("Requête envoyée : " + sendable);
        /*Response response = (Response) stream.getData();
        System.out.println(response);*/
    }
}
