package sendable.requests;

import java.io.*;

import sendable.Sendable;
import sendable.requests.name.RequestName;

public abstract class Request implements Sendable {
    protected String header;
    protected String body;
    protected RequestName name;
    protected String author;

    public Request(String header,RequestName name, String body,String author) {
        this.header = header;
        this.body = body;
        this.name = name;
        this.author = author;
    }

    public Request(String header,RequestName name,String author){
        this.header = header;
        this.body = "";
        this.name = name;
        this.author = author;
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
    public String getSender(){
        return author;
    }


    @Override
    public String toString(){
        return header + "\r\n" + body + "\r\n";
    }
}


