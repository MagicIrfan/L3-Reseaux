package sendable.flux;

import sendable.flux.name.FluxName;

import java.net.Socket;

public class UnsubscribeFlux extends Flux{

    private String receiver;
    public UnsubscribeFlux(String user,String receiver) {
        super("UNSUBSCRIBE author:"+receiver, FluxName.UNSUBSCRIBE,user);
        this.receiver = receiver;
    }

    public String getReceiver(){
        return receiver;
    }
}
