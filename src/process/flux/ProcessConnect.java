package process.flux;

import client.User;
import response.ConfirmationResponse;
import response.ErrorResponse;
import response.Response;
import sendable.Sendable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProcessConnect extends ProcessFlux{

    public ProcessConnect(Map<User, List<User>> subscribers, User user) {
        super(subscribers,user);
    }

    @Override
    public Response getResponse(Sendable sendable) {
        Response response = null;
        if(!subscribers.containsKey(user)){
            subscribers.put(user,new ArrayList<>());
            response = new ConfirmationResponse();
        }
        else {
            response = new ErrorResponse("Utilisateur déjà connecté !");
        }
        return response;
    }
}
