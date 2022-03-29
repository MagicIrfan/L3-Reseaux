package sendable.flux;

import sendable.flux.name.FluxName;

import java.net.Socket;

public class SubscribeFlux extends Flux{

    private String receiver;

    public SubscribeFlux(String user, String receiver) {
        super("SUBSCRIBE author:"+receiver, FluxName.SUBSCRIBE, user);
        this.receiver = receiver;
    }

    public String getReceiver(){
        return receiver;
    }

}
