package requests;

import requests.name.RequestName;

public class RepublishRequest extends Request{

    private String author;
    private long id;

    public RepublishRequest(long id, String author) {
        super("REPUBLISH author:"+author + " msg_id:"+id, RequestName.REPUBLISH);
        this.id = id;
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public long getId() {
        return id;
    }
}

