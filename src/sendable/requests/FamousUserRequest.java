package sendable.requests;

import sendable.requests.name.RequestName;

public class FamousUserRequest extends Request{
    public FamousUserRequest(String author) {
        super("FAMOUSNAME author:"+author, RequestName.FAMOUSNAME,author);
    }
}
