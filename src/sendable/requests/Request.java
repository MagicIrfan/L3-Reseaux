package sendable.requests;

import java.io.*;

import sendable.Sendable;
import sendable.requests.name.RequestName;

public abstract class Request implements Sendable {
    protected String header;
    protected String body;
    protected RequestName name;

    public Request(String header,RequestName name, String body) {
        this.header = header;
        this.body = body;
        this.name = name;
    }

    public Request(String header,RequestName name){
        this.header = header;
        this.body = "";
        this.name = name;
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


