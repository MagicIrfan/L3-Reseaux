package process;


import message.Message;
import requests.RcvMsgRequest;
import requests.Request;
import response.ErrorResponse;
import response.MessageResponse;
import response.Response;
import server.Database;

public class ProcessRcvMsg extends ProcessRequest{

    public ProcessRcvMsg(Database database) {
        super(database);
    }

    @Override
    public Response getResponse(Request receiver) {
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
