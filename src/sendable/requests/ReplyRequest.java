package sendable.requests;

import sendable.requests.name.RequestName;

import java.util.List;

public class ReplyRequest extends Request{

    private long id;
    private String author;
    private List<String> tags;

    public ReplyRequest(long id, String body, String author, List<String> tags) {
        super("REPLY author:"+author + "reply_to_id:"+id, RequestName.REPLY, body);
        this.id = id;
        this.author = author;
        this.tags = tags;

    }

    public long getId(){
        return id;
    }

    public String getAuthor(){
        return author;
    }

    public List<String> getTags(){
        return tags;
    }
}
