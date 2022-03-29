package process.request;

import sendable.Sendable;
import sendable.requests.RcvIdsRequest;
import response.ListIdMsgResponse;
import response.Response;
import server.data.Database;

import java.util.List;

public class ProcessRcvIds extends ProcessRequest {

    public ProcessRcvIds(Database database) {
        super(database);
    }

    @Override
    public Response getResponse(Sendable receiver) {
        RcvIdsRequest receive = (RcvIdsRequest) receiver;
        List<Long> listIDS = database.getIds(receive.getAuthor(),receive.getTags(),receive.getSince(),receive.getLimit());
        return new ListIdMsgResponse(listIDS.toString());
    }


}
