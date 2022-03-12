package process.request;

import message.Message;
import process.request.ProcessRequest;
import sendable.Sendable;
import sendable.requests.ReplyRequest;
import response.ConfirmationResponse;
import response.ErrorResponse;
import response.Response;
import server.Database;

public class ProcessReply extends ProcessRequest {


    private long id;

    public ProcessReply(Database database, long id) {
        super(database);
        this.id = id;
    }

    @Override
    public Response getResponse(Sendable receiver) {
        ReplyRequest replyRequest = (ReplyRequest) receiver;
        Response response;
        if(!database.idExists(replyRequest.getId())){
            response = new ErrorResponse("l'ID n'existe pas");
        }
        else{
            Message message = new Message(id,replyRequest.getAuthor(),replyRequest.getBody(),replyRequest.getTags());
            database.addMessage(message);
            response = new ConfirmationResponse();
            System.out.println(message);
        }
        return response;
    }
}
