package sendable.requests;

import sendable.requests.name.RequestName;

public class RcvMsgRequest extends Request{

    private long id;

    public RcvMsgRequest(long id,String author) {
        super("RCV_MSG msg_id:" + id, RequestName.RCV_MSG,author);
        this.id = id;
    }

    public long getId(){
        return id;
    }

}
