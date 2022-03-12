package sendable.flux;

import sendable.flux.name.FluxName;

public class UnsubscribeFlux extends Flux{

    public UnsubscribeFlux(String user) {
        super("UNSUBSCRIBE author:"+user, FluxName.UNSUBSCRIBE,user);
    }
}
