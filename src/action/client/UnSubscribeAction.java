package action.client;

import Tools.Name;
import sendable.Sendable;
import sendable.flux.UnsubscribeFlux;
import stream.Stream;

import java.io.IOException;

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
    }
}
