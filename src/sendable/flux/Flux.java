package sendable.flux;

import sendable.Sendable;
import sendable.flux.name.FluxName;

public abstract class Flux implements Sendable {
    protected String header;
    protected FluxName name;
    protected String userName;

    public Flux(String header, FluxName name, String userName) {
        this.header = header;
        this.name = name;
        this.userName = userName;
    }

    @Override
    public String getName() {return name.toString();}


    @Override
    public String getSender(){
        return userName;
    }

    @Override
    public String toString(){
        return header + "\r\n";
    }
}
