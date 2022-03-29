package action.server;

import action.client.UnSubscribeAction;
import client.User;
import process.Process;
import process.flux.ProcessUnsubscribe;
import response.Response;
import sendable.Sendable;
import sendable.flux.UnsubscribeFlux;
import server.data.Database;
import stream.Stream;

import java.io.IOException;
import java.net.Socket;

public class UnSubscribeServerAction extends ServerAction {

    private User user;
    public UnSubscribeServerAction(Database database, Sendable sendable, Stream stream, User user) {
        super(database, sendable, stream);
        this.user = user;
    }

    @Override
    public void doAction() throws IOException, ClassNotFoundException, InterruptedException {
        UnsubscribeFlux flux = (UnsubscribeFlux) sendable;
        Socket userSocket = database.getSocketWithName(flux.getReceiver());
        User user2 = new User(flux.getReceiver(),userSocket);
        Process process = new ProcessUnsubscribe(database,user, user2);
        Response response = process.getResponse(flux);
        stream.writeData(response);
    }
}
