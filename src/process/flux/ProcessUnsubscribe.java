package process.flux;

import client.User;
import response.ConfirmationResponse;
import response.ErrorResponse;
import response.Response;
import sendable.Sendable;
import server.data.Database;

public class ProcessUnsubscribe extends ProcessFlux{

    private User user2;
    public ProcessUnsubscribe(Database database,User user, User user2) {
        super(database,user);
        this.user2 = user2;
    }

    @Override
    public Response getResponse(Sendable sendable) {
        if(!database.containsConnectedUser(client))
            return new ErrorResponse(client.getName()+ " n'existe pas");

        if(!database.containsConnectedUser(user2))
            return new ErrorResponse(user2.getName()+ " n'existe pas");

        if(!database.subscriberExists(client, user2))
            return new ErrorResponse(client.getName() + " n'est pas abonné à " + user2.getName());


        database.unsubscribe(client,user2);
        return new ConfirmationResponse();
    }
}
