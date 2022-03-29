package client.action;

import response.Response;
import sendable.Sendable;
import sendable.flux.ConnectFlux;
import sendable.flux.ShowMsgFlux;
import sendable.requests.RcvMsgRequest;
import sendable.requests.Request;
import stream.Stream;

import java.io.IOException;

public class ShowMessagesAction extends ClientAction{

    public ShowMessagesAction(Stream stream, String userName) throws IOException {
        super(stream, userName);
    }

    @Override
    public void doAction() throws IOException, ClassNotFoundException {
        Sendable sendable = new ShowMsgFlux(userName);
        stream.writeData(sendable);
        System.out.println("Requête envoyée : " + sendable);
        /*Response response = (Response) stream.getData();
        System.out.println(response);*/
    }
}
