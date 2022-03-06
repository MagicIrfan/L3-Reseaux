package process;

import requests.RcvIdsRequest;
import requests.Request;
import response.ListIdMsgResponse;
import response.Response;
import server.Database;

import java.util.List;

public class ProcessRcvIds extends ProcessRequest {

    public ProcessRcvIds(Database database) {
        super(database);
    }

    @Override
    public Response getResponse(Request receiver) {
        RcvIdsRequest receive = (RcvIdsRequest) receiver;
        List<Long> listIDS = database.getIds(receive.getAuthor(),receive.getTags(),receive.getSince(),receive.getLimit());
        return new ListIdMsgResponse(listIDS.toString());
    }


}
