package action.server;

import client.User;
import process.Process;
import process.flux.ProcessSubscribe;
import process.flux.ProcessUnsubscribe;
import response.Response;
import sendable.Sendable;
import sendable.flux.SubscribeFlux;
import sendable.flux.UnsubscribeFlux;
import server.data.Database;
import stream.Stream;

import java.io.IOException;
import java.net.Socket;

public class SubscribeServerAction extends ServerAction{
    private User user;
    public SubscribeServerAction(Database database, Sendable sendable, Stream stream, User user) {
        super(database, sendable, stream);
        this.user = user;
    }

    @Override
    public void doAction() throws IOException, ClassNotFoundException, InterruptedException {
        SubscribeFlux flux = (SubscribeFlux) sendable;
        Socket userSocket = database.getSocketWithName(flux.getReceiver());

        User user2 = new User(flux.getReceiver(),userSocket);
        System.out.println("user : " + user);
        System.out.println("user2 : " + user2);
        Process process = new ProcessSubscribe(database,user,user2);
        Response response = process.getResponse(flux);
        stream.writeData(response);
    }
}
