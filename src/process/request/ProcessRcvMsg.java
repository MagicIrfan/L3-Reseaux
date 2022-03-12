package process.request;


import message.Message;
import process.request.ProcessRequest;
import sendable.Sendable;
import sendable.requests.RcvMsgRequest;
import response.ErrorResponse;
import response.MessageResponse;
import response.Response;
import server.Database;

public class ProcessRcvMsg extends ProcessRequest {

    public ProcessRcvMsg(Database database) {
        super(database);
    }

    @Override
    public Response getResponse(Sendable receiver) {
        RcvMsgRequest receive = (RcvMsgRequest) receiver;
        Response response;
        long ids = receive.getId();
        if(database.idExists(ids)){
            Message message = database.getMessageWithId(ids);
            response = new MessageResponse(message.toString());
        }
        else{
            response = new ErrorResponse("l'id du message n'existe pas");
        }
        return response;
    }

}
