package requests;
import java.util.*;

public class Message {

    private String header;
    private String author;
    private String receiver;
    private String body;
    private String response;
    private List<String> tags;
    private long id;

    public Message(long id,String header, String author,String receiver, String body, String response){
        this.header = header;
        this.id = id;
        this.author = author;
        this.receiver = receiver;
        this.body = body;
        this.response = response;
        this.tags = new ArrayList<>();

    }

    public String getHeader(){
        return header;
    }

    public String getAuthor(){
        return author;
    }

    public String getReceiver(){
        return receiver;
    }

    public String getBody(){
        return body;
    }

    public String getResponse(){
        return response;
    }

    public List<String> getTags(){
        return tags;
    }

    public long getId(){
        return id;
    }

    @Override
    public String toString(){
        return null;
    }
}
