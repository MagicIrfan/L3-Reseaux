package process.flux;

import client.User;
import response.ConfirmationResponse;
import response.ErrorResponse;
import response.Response;
import sendable.Sendable;
import server.data.Database;

public class ProcessConnect extends ProcessFlux{

    public ProcessConnect(Database database,User user) {
        super(database,user);
    }

    @Override
    public Response getResponse(Sendable sendable) {
        if(database.userExists(client)) {
            return new ErrorResponse("L'utilisateur " + client + " est déjà connecté !");
        }
        else{
            database.addUser(client);
            database.connectUser(client);
        }

        return new ConfirmationResponse();
    }
}
