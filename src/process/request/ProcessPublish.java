package process.request;

import message.Message;
import sendable.Sendable;
import sendable.requests.PublishRequest;
import response.ConfirmationResponse;
import response.ErrorResponse;
import response.Response;
import server.Database;

public class ProcessPublish extends ProcessRequest {

    private long id;

    public ProcessPublish(long id,Database database){
        super(database);
        this.id = id;
    }


    @Override
    public Response getResponse(Sendable receiver) {
        Response response;
        PublishRequest publish = (PublishRequest) receiver;
        if(database.idExists(id)){
            response = new ErrorResponse("l'ID existe déjà");
        }
        else{
            Message message = new Message(id,publish.getAuthor(),publish.getBody(),publish.getTags());
            database.addMessage(message);
            System.out.println(message + " id:" + message.getId());
            response = new ConfirmationResponse();
        }
        return response;
    }

}
