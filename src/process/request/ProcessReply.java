package process.request;

import message.Message;
import sendable.Sendable;
import sendable.requests.ReplyRequest;
import response.ConfirmationResponse;
import response.ErrorResponse;
import response.Response;
import server.data.Database;

public class ProcessReply extends ProcessRequest {


    private long id;

    public ProcessReply(Database database, long id) {
        super(database);
        this.id = id;
    }

    @Override
    public Response getResponse(Sendable receiver) {
        ReplyRequest replyRequest = (ReplyRequest) receiver;

        if(!database.idExists(replyRequest.getId())){
            return new ErrorResponse("l'ID " + replyRequest.getId() + " n'existe pas");
        }

        Message message = new Message(id,replyRequest.getSender(),replyRequest.getBody(),replyRequest.getTags());
        database.addMessage(message);
        System.out.println(message);
        return new ConfirmationResponse();

    }
}
