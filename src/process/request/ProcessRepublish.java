package process.request;

import client.User;
import message.Message;
import sendable.Sendable;
import sendable.requests.RepublishRequest;
import response.ConfirmationResponse;
import response.ErrorResponse;
import response.Response;
import server.MicroblogAMUCentral;
import server.data.Database;

public class ProcessRepublish extends ProcessRequest {
    private User user;
    public ProcessRepublish(Database database, User user) {
        super(database);
        this.user = user;
    }

    @Override
    public Response getResponse(Sendable receiver) {
        RepublishRequest republishRequest = (RepublishRequest) receiver;
        if(database.idExists(republishRequest.getId())){
            Message republished = database.getMessageWithId(republishRequest.getId());
            Message message = new Message(MicroblogAMUCentral.atomicID.getAndIncrement(),republished.getAuthor(),republished.getBody(),republished.getTags());
            database.addMessage(message);
            database.addMessageToUser(user,message);
            database.setReceivedMessages(user);
            database.getNbOfRepublished().put(republishRequest.getId(),database.getNbOfRepublished().get(republishRequest.getId()) + 1);
            System.out.println(message + " id:" + message.getId());
            return new ConfirmationResponse();
        }
        return new ErrorResponse("L'id n'existe pas");
    }
}
