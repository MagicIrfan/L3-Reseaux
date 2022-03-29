package process.request;


import message.Message;
import sendable.Sendable;
import sendable.requests.RcvMsgRequest;
import response.ErrorResponse;
import response.MessageResponse;
import response.Response;
import server.data.Database;

public class ProcessRcvMsg extends ProcessRequest {

    public ProcessRcvMsg(Database database) {
        super(database);
    }

    @Override
    public Response getResponse(Sendable receiver) {
        RcvMsgRequest receive = (RcvMsgRequest) receiver;

        long ids = receive.getId();

        if(database.idExists(ids)){
            Message message = database.getMessageWithId(ids);
            return new MessageResponse(message.toString());
        }

        return new ErrorResponse("l'id " + ids + " du message n'existe pas");

    }

}
