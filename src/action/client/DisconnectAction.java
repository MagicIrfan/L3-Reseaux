package action.client;

import response.Response;
import sendable.Sendable;
import sendable.flux.ConnectFlux;
import sendable.flux.DisconnectFlux;
import stream.Stream;

import java.io.IOException;

public class DisconnectAction extends ClientAction{
    private String userName;
    public DisconnectAction(Stream stream,String userName) throws IOException {
        super(stream);
        this.userName = userName;
    }

    @Override
    public void doAction() throws IOException, ClassNotFoundException {
        Sendable sendable = new DisconnectFlux(userName);
        stream.writeData(sendable);
        System.out.println("Requête envoyée : " + sendable);
        Response response = (Response) stream.getData();
        System.out.println(response);
    }
}
