package process.request;

import client.User;
import message.Message;
import sendable.Sendable;
import sendable.requests.ReplyRequest;
import response.ConfirmationResponse;
import response.ErrorResponse;
import response.Response;
import server.data.Database;

public class ProcessReply extends ProcessRequest {


    private long id;
    private User user;

    public ProcessReply(Database database, long id, User user) {
        super(database);
        this.id = id;
        this.user = user;
    }

    @Override
    public Response getResponse(Sendable receiver) {
        ReplyRequest replyRequest = (ReplyRequest) receiver;

        if(!database.idExists(replyRequest.getId())){
            return new ErrorResponse("l'ID " + replyRequest.getId() + " n'existe pas");
        }

        Message message = new Message(id,replyRequest.getSender(),replyRequest.getBody(),replyRequest.getTags());
        database.addMessage(message);
        database.addMessageToUser(user,message);
        database.setReceivedMessages(user);
        System.out.println(message + " id:" + message.getId());
        return new ConfirmationResponse();

    }
}
