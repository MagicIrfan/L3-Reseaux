package action.client;

import action.client.ClientAction;
import sendable.Sendable;
import sendable.flux.ConnectFlux;
import stream.Stream;

import java.io.IOException;

public class ConnectAction extends ClientAction {


    private String userName;
    public ConnectAction(Stream stream, String userName) throws IOException {
        super(stream);
        this.userName = userName;
    }

    @Override
    public void doAction() throws IOException, ClassNotFoundException {
        Sendable sendable = new ConnectFlux(userName);
        stream.writeData(sendable);
        System.out.println("Requête envoyée : " + sendable);
    }
}
