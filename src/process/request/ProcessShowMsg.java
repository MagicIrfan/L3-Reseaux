package process.request;

import client.User;
import response.ListMsgResponse;
import response.Response;
import sendable.Sendable;
import server.data.Database;

public class ProcessShowMsg extends ProcessRequest{

    private User client;

    public ProcessShowMsg(Database database, User client) {
        super(database);
        this.client = client;
    }

    @Override
    public Response getResponse(Sendable sendable) {
        Response response = new ListMsgResponse(database.getReceivedMessages().get(database.getConnectedUser(client)).toString());
        database.getReceivedMessages().get(database.getConnectedUser(client)).clear();
        return response;
    }
}
