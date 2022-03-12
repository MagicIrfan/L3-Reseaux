package process.flux;

import client.User;
import response.ConfirmationResponse;
import response.ErrorResponse;
import response.Response;
import sendable.Sendable;

import java.util.List;
import java.util.Map;

public class ProcessUnsubscribe extends ProcessFlux{

    private User user2;
    public ProcessUnsubscribe(Map<User, List<User>> subscribers,User user, User user2) {
        super(subscribers,user);
        this.user2 = user2;
    }

    @Override
    public Response getResponse(Sendable sendable) {
        Response response = null;
        if(subscribers.get(user).contains(user2)){
            subscribers.get(user).remove(user2);
            response = new ConfirmationResponse();
        }
        else{
            response = new ErrorResponse(user + " n'est pas abonné à " + user2);
        }
        return response;
    }
}
