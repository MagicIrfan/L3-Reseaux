package sendable.requests;

import sendable.flux.name.FluxName;
import sendable.requests.name.RequestName;

public class ShowMsgFlux extends Request{
    public ShowMsgFlux(String userName) {
        super("SHOW_MSG user:"+userName, RequestName.SHOW_MSG,userName);
    }
}
