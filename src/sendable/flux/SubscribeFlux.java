package sendable.flux;

import sendable.flux.name.FluxName;

public class SubscribeFlux extends Flux{

    public SubscribeFlux(String user) {
        super("SUBSCRIBE author:"+user, FluxName.SUBSCRIBE, user);
    }
}
