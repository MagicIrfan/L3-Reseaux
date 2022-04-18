package sendable.flux;

import sendable.flux.name.FluxName;

public class DisconnectFlux extends Flux{
    public DisconnectFlux(String userName) {
        super("DISCONNECT user:"+userName, FluxName.DISCONNECT, userName);
    }
}
