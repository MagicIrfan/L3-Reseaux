package action.server;

import client.User;
import process.flux.ProcessDisconnect;
import process.flux.ProcessFlux;
import process.request.ProcessFamousUser;
import process.request.ProcessRequest;
import response.Response;
import sendable.Sendable;
import server.data.Database;
import stream.Stream;

import java.io.IOException;

public class DisconnectServerAction extends ServerAction{
    private User user;
    public DisconnectServerAction(Database database, Sendable sendable, Stream stream,User user) {
        super(database, sendable, stream);
        this.user = user;
    }

    @Override
    public void doAction() throws IOException, ClassNotFoundException, InterruptedException {
        ProcessFlux processRequest = new ProcessDisconnect(database,user);
        Response response = processRequest.getResponse(sendable);
        stream.writeData(response);
    }


}
