package action.client;

import Tools.Name;
import action.client.ClientAction;
import sendable.Sendable;
import sendable.flux.SubscribeFlux;
import stream.Stream;

import java.io.IOException;

public class SubscribeAction extends ClientAction {


    public SubscribeAction(Stream stream, String user) throws IOException {
        super(stream, user);
    }

    @Override
    public void doAction() throws IOException, ClassNotFoundException {
        System.out.println("Saisissez le pseudo auquel vous voulez vous abonner");
        String receiver = Name.getName();
        Sendable flux = new SubscribeFlux(userName,receiver);
        stream.writeData(flux);
        System.out.println("Requete envoyée : " + flux);

    }
}
