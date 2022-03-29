package action.server;

import client.User;
import process.Process;
import process.flux.ProcessConnect;
import response.ConfirmationResponse;
import response.Response;
import sendable.Sendable;
import sendable.flux.ConnectFlux;
import server.data.Database;
import stream.Stream;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

public class ConnectServerAction extends ServerAction{

    private User user;
    private ConcurrentHashMap<User, Stream> userStream;

    public ConnectServerAction(Database database, Sendable sendable, User user,ConcurrentHashMap<User, Stream> userStream,Stream stream) {
        super(database,sendable,stream);
        this.user = user;
        this.userStream = userStream;
    }

    @Override
    public void doAction() throws IOException, ClassNotFoundException, InterruptedException {
        ConnectFlux flux = (ConnectFlux) sendable;
        Process process = new ProcessConnect(database,user);
        Response response = process.getResponse(flux);
        if(response instanceof ConfirmationResponse)
            userStream.put(database.getConnectedUser(user),stream);
        stream.writeData(response);
    }
}
