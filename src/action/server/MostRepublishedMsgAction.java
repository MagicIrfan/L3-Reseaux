package action.server;

import process.request.ProcessFamousUser;
import process.request.ProcessMostRepublishMsg;
import process.request.ProcessRequest;
import response.Response;
import sendable.Sendable;
import sendable.requests.FamousUserRequest;
import sendable.requests.MostRepublishedMsgRequest;
import server.data.Database;
import stream.Stream;

import java.io.IOException;

public class MostRepublishedMsgAction extends ServerAction{

    public MostRepublishedMsgAction(Database database, Sendable sendable, Stream stream) {
        super(database, sendable, stream);
    }

    @Override
    public void doAction() throws IOException, ClassNotFoundException, InterruptedException {
        MostRepublishedMsgRequest request = (MostRepublishedMsgRequest) sendable;
        ProcessRequest processRequest = new ProcessMostRepublishMsg(database);
        Response response = processRequest.getResponse(request);
        stream.writeData(response);
    }
}
