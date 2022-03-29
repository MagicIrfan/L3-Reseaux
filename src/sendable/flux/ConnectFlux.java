package sendable.flux;

import sendable.flux.name.FluxName;

import java.net.Socket;

public class ConnectFlux extends Flux{

    public ConnectFlux(String user) {
        super("CONNECT user:"+user, FluxName.CONNECT, user);
    }
}
