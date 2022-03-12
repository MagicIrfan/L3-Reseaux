package client.action;

import Tools.Name;
import client.User;
import response.Response;
import sendable.Sendable;
import sendable.flux.SubscribeFlux;
import stream.Stream;

import java.io.IOException;
import java.net.Socket;

public class SubscribeAction extends ClientAction{


    public SubscribeAction(Stream stream, User user) throws IOException {
        super(stream, user);
    }

    @Override
    public void doAction() throws IOException, ClassNotFoundException {
        System.out.println("Saisissez le pseudo auquel vous voulez vous abonner");
        String name = "@"+ Name.getName();
        Sendable flux = new SubscribeFlux(name);
        sendPacket(flux);

        System.out.println("Requete envoyée : " + flux);
        Response response =  (Response) stream.getData();
        System.out.println(response);
    }
}
