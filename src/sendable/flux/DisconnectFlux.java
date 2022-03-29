package sendable.flux;

import sendable.flux.name.FluxName;

public class DisconnectFlux extends Flux{

    public DisconnectFlux(String user) {
        super("DISCONNECT user:"+user, FluxName.DISCONNECT, user);
    }
}
