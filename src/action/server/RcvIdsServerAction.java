package action.server;

import process.request.ProcessRcvIds;
import process.request.ProcessRequest;
import response.Response;
import sendable.Sendable;
import server.data.Database;
import stream.Stream;

import java.io.IOException;

public class RcvIdsServerAction extends ServerAction{

    public RcvIdsServerAction(Database database, Sendable sendable, Stream stream) {
        super(database, sendable,stream);
    }

    @Override
    public void doAction() throws IOException, ClassNotFoundException, InterruptedException {
        ProcessRequest processRequest = new ProcessRcvIds(database);
        Response response = processRequest.getResponse(sendable);
        stream.writeData(response);
    }
}
