package process.request;

import message.Message;
import sendable.Sendable;
import sendable.requests.RepublishRequest;
import response.ConfirmationResponse;
import response.ErrorResponse;
import response.Response;
import server.data.Database;

public class ProcessRepublish extends ProcessRequest {
    public ProcessRepublish(Database database) {
        super(database);
    }

    @Override
    public Response getResponse(Sendable receiver) {
        RepublishRequest republishRequest = (RepublishRequest) receiver;
        if(database.idExists(republishRequest.getId())){
            Message message = database.getMessageWithId(republishRequest.getId());
            System.out.println(message);
            return new ConfirmationResponse();
        }
        return new ErrorResponse("L'id n'existe pas");
    }
}
