package message;
import java.io.Serializable;
import java.util.*;

public class Message implements Serializable {

    private String author;
    private String body;
    private List<String> tags;
    private long id;

    public Message(long id, String author,String body, List<String> tags){
        this.author = author;
        this.body = body;
        this.tags = tags;
        this.id = id;

    }


    public String getAuthor(){
        return author;
    }

    public String getBody(){
        return body;
    }

    public List<String> getTags(){
        return tags;
    }

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("From : " + author + "\r\n" + "Tags : " + tags + "\r\n" + "Body : " + body + "\r\n");
        return builder.toString();
    }
}
