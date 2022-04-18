package action.server;

import client.User;
import process.request.ProcessPublish;
import process.request.ProcessRequest;
import response.ConfirmationResponse;
import response.ErrorResponse;
import response.Response;
import sendable.Sendable;
import server.MicroblogAMUCentral;
import server.data.Database;
import stream.Stream;

import java.io.IOException;

public class PublishServerAction extends ServerAction{

    private User user;
    private MicroblogAMUCentral parent;
    public PublishServerAction(Database database, Sendable sendable, User user,MicroblogAMUCentral parent,Stream stream) {
        super(database, sendable,stream);
        this.user = user;
        this.parent = parent;
    }

    @Override
    public void doAction() throws IOException, ClassNotFoundException, InterruptedException {
        long randomID = MicroblogAMUCentral.atomicID.getAndIncrement();
        User newUser = database.getConnectedUser(user);
        ProcessRequest process = new ProcessPublish(randomID, database,newUser);
        Response response = process.getResponse(sendable);
        if(response instanceof ErrorResponse)
            randomID = MicroblogAMUCentral.atomicID.decrementAndGet();
        stream.writeData(response);
    }
}
