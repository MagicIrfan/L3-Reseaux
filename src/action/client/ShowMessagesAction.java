package action.client;

import action.client.ClientAction;
import sendable.Sendable;
import sendable.flux.ShowMsgFlux;
import stream.Stream;

import java.io.IOException;

public class ShowMessagesAction extends ClientAction {

    public ShowMessagesAction(Stream stream, String userName) throws IOException {
        super(stream, userName);
    }

    @Override
    public void doAction() throws IOException, ClassNotFoundException {
        Sendable sendable = new ShowMsgFlux(userName);
        stream.writeData(sendable);
        System.out.println("Requête envoyée : " + sendable);
    }
}
