package sendable.flux;

import sendable.Sendable;
import sendable.flux.name.FluxName;
import sendable.requests.name.RequestName;

public abstract class Flux implements Sendable {
    protected String header;
    protected String body;
    protected FluxName name;
    protected String userName;

    public Flux(String header, FluxName name, String body, String userName) {
        this.header = header;
        this.body = body;
        this.name = name;
        this.userName = userName;
    }

    public Flux(String header,FluxName name, String userName){
        this.header = header;
        this.body = "";
        this.name = name;
        this.userName = userName;
    }

    public String getUserName(){
        return userName;
    }

    public String getHeader(){
        return header;
    }

    public String getBody(){
        return body;
    }

    @Override
    public String getName() {return name.toString();}



    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append(header + "\r\n" + body + "\r\n");
        return builder.toString();
    }
}
