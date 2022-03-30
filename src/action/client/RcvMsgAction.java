package action.client;

import action.client.ClientAction;
import sendable.requests.RcvMsgRequest;
import sendable.requests.Request;
import stream.Stream;

import java.io.IOException;

public class RcvMsgAction extends ClientAction {


    private String userName;
    public RcvMsgAction(Stream stream, String user) throws IOException {
        super(stream);
        this.userName = user;
    }

    @Override
    public void doAction() throws IOException, ClassNotFoundException {
        System.out.println("Saisir l'id du message");
        long id = Long.parseLong(reader.readLine());
        Request request = new RcvMsgRequest(id,userName);
        //stream.writeData(request);
        stream.writeData(request);
        System.out.println("Requete envoyée : " + request);
    }
}
