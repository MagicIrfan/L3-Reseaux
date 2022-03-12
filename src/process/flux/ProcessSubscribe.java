package process.flux;

import client.User;
import response.ConfirmationResponse;
import response.ErrorResponse;
import response.Response;
import sendable.Sendable;

import java.util.List;
import java.util.Map;

public class ProcessSubscribe extends ProcessFlux{

    private User user2;
    public ProcessSubscribe(Map<User, List<User>> subscribers,User user, User user2) {
        super(subscribers,user);
        this.user2 = user2;
    }

    @Override
    public Response getResponse(Sendable sendable) {
        Response response = null;
        if(subscribers.containsKey(user2)){
            subscribers.get(user).add(user2);
            response = new ConfirmationResponse();
        }
        else{
            response = new ErrorResponse(user2 + " n'est pas géré par l'application");
        }
        return null;
    }
}
