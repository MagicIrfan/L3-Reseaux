package process;

import message.Message;
import requests.RepublishRequest;
import requests.Request;
import response.ConfirmationResponse;
import response.ErrorResponse;
import response.Response;
import server.Database;

public class ProcessRepublish extends ProcessRequest{
    public ProcessRepublish(Database database) {
        super(database);
    }

    @Override
    public Response getResponse(Request receiver) {
        RepublishRequest republishRequest = (RepublishRequest) receiver;
        Response response;
        if(!database.idExists(republishRequest.getId())){
            response = new ErrorResponse("l'ID n'existe pas");
        }
        else{
            Message message = database.getMessageWithId(republishRequest.getId());
            response = new ConfirmationResponse();
            System.out.println(message);
        }
        return response;
    }
}
