package action.server;

import process.request.ProcessFamousUser;
import process.request.ProcessRcvIds;
import process.request.ProcessRequest;
import response.Response;
import sendable.Sendable;
import sendable.requests.FamousUserRequest;
import server.data.Database;
import stream.Stream;

import java.io.IOException;

public class FamousUserServerAction extends ServerAction{

    public FamousUserServerAction(Database database, Sendable sendable, Stream stream) {
        super(database, sendable, stream);
    }

    @Override
    public void doAction() throws IOException, ClassNotFoundException, InterruptedException {
        FamousUserRequest request = (FamousUserRequest) sendable;
        ProcessRequest processRequest = new ProcessFamousUser(database);
        Response response = processRequest.getResponse(request);
        stream.writeData(response);
    }
}
