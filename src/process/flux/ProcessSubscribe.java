package process.flux;

import client.User;
import response.ConfirmationResponse;
import response.ErrorResponse;
import response.Response;
import sendable.Sendable;
import server.data.Database;

public class ProcessSubscribe extends ProcessFlux{

    private User user2;
    public ProcessSubscribe(Database database, User user, User user2) {
        super(database,user);
        this.user2 = user2;
    }

    @Override
    public Response getResponse(Sendable sendable) {
        if(!database.containsConnectedUser(user2))
            return new ErrorResponse(user2.getName() + " n'est pas géré par l'application");

        if(database.subscriberExists(client, user2))
            return new ErrorResponse(client.getName() + " est déjà abonné à " + user2.getName());

        if(client.getName().equals(user2.getName()))
            return new ErrorResponse("Tu veux t'abonner à toi même ???");


        database.subscribe(client,user2);
        return new ConfirmationResponse();

    }
}
