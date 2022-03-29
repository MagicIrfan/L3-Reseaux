package client.action;

import Tools.Name;
import client.User;
import response.Response;
import sendable.Sendable;
import sendable.flux.UnsubscribeFlux;
import stream.Stream;

import java.io.IOException;
import java.net.Socket;

public class UnSubscribeAction extends ClientAction {


    public UnSubscribeAction(Stream stream, String user) throws IOException {
        super(stream, user);
    }

    @Override
    public void doAction() throws IOException, ClassNotFoundException {
        System.out.println("Saisissez le pseudo auquel vous voulez vous désabonner");
        String receiver = Name.getName();
        Sendable flux = new UnsubscribeFlux(userName,receiver);
        stream.writeData(flux);

        System.out.println("Requete envoyée : " + flux);
        /*Response response = (Response) stream.getData();
        System.out.println(response);*/
    }
}
