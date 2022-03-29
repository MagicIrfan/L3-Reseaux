package action.server;

import client.User;
import process.request.ProcessRepublish;
import process.request.ProcessRequest;
import response.ConfirmationResponse;
import response.Response;
import sendable.Sendable;
import sendable.requests.RepublishRequest;
import server.MicroblogAMUCentral;
import server.data.Database;
import stream.Stream;

import java.io.IOException;

public class RepublishServerAction extends ServerAction{
    private User user;
    private MicroblogAMUCentral parent;
    public RepublishServerAction(Database database, Sendable sendable, Stream stream,User user, MicroblogAMUCentral parent) {
        super(database, sendable, stream);
        this.user = user;
        this.parent = parent;
    }

    @Override
    public void doAction() throws IOException, ClassNotFoundException, InterruptedException {
        RepublishRequest flux = (RepublishRequest) sendable;
        ProcessRequest processRequest = new ProcessRepublish(database);
        Response response = processRequest.getResponse(flux);
        User newUser = database.getConnectedUser(user);
        if(response instanceof ConfirmationResponse && !database.getMessagesMap().get(newUser).isEmpty())
            parent.sendMessagesToClient(user);
        stream.writeData(response);
    }
}
