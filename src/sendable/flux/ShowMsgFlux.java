package sendable.flux;

import sendable.flux.name.FluxName;

public class ShowMsgFlux extends Flux{
    public ShowMsgFlux(String userName) {
        super("SHOW_MSG user:"+userName,FluxName.SHOW_MSG,userName);
    }
}
