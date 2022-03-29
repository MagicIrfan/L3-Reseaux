package process.flux;

import client.User;
import response.ConfirmationResponse;
import response.ErrorResponse;
import response.Response;
import sendable.Sendable;
import server.data.Database;

public class ProcessDisconnect extends ProcessFlux{

    public ProcessDisconnect(Database database, User client) {
        super(database, client);
    }

    @Override
    public Response getResponse(Sendable sendable) {
        if(!database.containsConnectedUser(client))
            return new ErrorResponse(client.getName() + "n'existe pas !");

        database.disconnectClient(client);
        return new ConfirmationResponse();
    }
}
