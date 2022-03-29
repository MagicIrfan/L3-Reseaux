package sendable.requests;

import sendable.requests.name.RequestName;

public class RepublishRequest extends Request{

    private String author;
    private long id;

    public RepublishRequest(long id, String author) {
        super("REPUBLISH author:"+author + " msg_id:"+id, RequestName.REPUBLISH,author);
        this.id = id;
        this.author = author;
    }

    public long getId() {
        return id;
    }

    @Override
    public String getSender() {
        return author;
    }
}

