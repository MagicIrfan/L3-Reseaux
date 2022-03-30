package process.request;

import client.User;
import message.Message;
import sendable.Sendable;
import sendable.requests.PublishRequest;
import response.ConfirmationResponse;
import response.ErrorResponse;
import response.Response;
import server.data.Database;

import java.io.IOException;

public class ProcessPublish extends ProcessRequest {

    private long id;
    private User user;

    public ProcessPublish(long id,Database database,User user){
        super(database);
        this.id = id;
        this.user = user;
    }


    @Override
    public Response getResponse(Sendable receiver) {
        PublishRequest publish = (PublishRequest) receiver;

        if(database.idExists(id))
            return new ErrorResponse("l'ID existe déjà");

        Message message = new Message(id,publish.getSender(),publish.getBody(),publish.getTags());
        database.addMessage(message);
        database.addMessageToUser(user,message);
        database.setReceivedMessages(user);
        System.out.println(message + " id:" + message.getId());
        return new ConfirmationResponse();
    }

}
