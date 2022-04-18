package sendable.requests;

import sendable.requests.name.RequestName;

public class MostRepublishedMsgRequest extends Request{
    public MostRepublishedMsgRequest(String author) {
        super("MOST_REPUBLISHED_MSG", RequestName.MOST_REPUBLISHED_MSG, author);
    }
}
