package process.flux;

import client.User;
import response.ConfirmationResponse;
import response.Response;
import sendable.Sendable;
import server.data.Database;
import stream.Stream;

public class ProcessDisconnect extends ProcessFlux{
    public ProcessDisconnect(Database database, User user) {
        super(database,user);
    }

    @Override
    public Response getResponse(Sendable sendable) {
        return new ConfirmationResponse();
    }
}
