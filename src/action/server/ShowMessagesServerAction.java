package action.server;

import client.User;
import process.Process;
import process.flux.ProcessShowMsg;
import response.Response;
import sendable.Sendable;
import sendable.flux.ShowMsgFlux;
import server.data.Database;
import stream.Stream;

import java.io.IOException;

public class ShowMessagesServerAction extends ServerAction{
    private User user;
    public ShowMessagesServerAction(Database database, Sendable sendable, Stream stream, User user) {
        super(database, sendable, stream);
        this.user = user;
    }

    @Override
    public void doAction() throws IOException, ClassNotFoundException, InterruptedException {
        ShowMsgFlux flux = (ShowMsgFlux) sendable;
        Process process = new ProcessShowMsg(database,user);
        Response response = process.getResponse(flux);
        stream.writeData(response);
    }
}
