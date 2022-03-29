package action.server;

import client.User;
import process.request.ProcessReply;
import process.request.ProcessRequest;
import response.ConfirmationResponse;
import response.ErrorResponse;
import response.Response;
import sendable.Sendable;
import server.MicroblogAMUCentral;
import server.data.Database;
import stream.Stream;

import java.io.IOException;

public class ReplyServerAction extends ServerAction{

    private User user;
    private MicroblogAMUCentral parent;
    public ReplyServerAction(Database database, Sendable sendable, Stream stream, User user,MicroblogAMUCentral parent) {
        super(database, sendable, stream);
        this.user = user;
        this.parent = parent;
    }

    @Override
    public void doAction() throws IOException, ClassNotFoundException, InterruptedException {
        long randomID = MicroblogAMUCentral.atomicID.getAndIncrement();
        ProcessRequest processRequest = new ProcessReply(database, randomID);
        Response response = processRequest.getResponse(sendable);
        User newUser = database.getConnectedUser(user);
        if(response instanceof ErrorResponse)
            randomID = MicroblogAMUCentral.atomicID.getAndDecrement();
        if(response instanceof ConfirmationResponse && !database.getMessagesMap().get(newUser).isEmpty())
            parent.sendMessagesToClient(user);
        stream.writeData(response);
    }
}
