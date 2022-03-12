package sendable.requests;

import sendable.requests.name.RequestName;

import java.util.*;

public class PublishRequest extends Request {

    private String author;
    private List<String> tags;

    public PublishRequest(String author, String body, List<String> tags){
        super( RequestName.PUBLISH + " author " + author,RequestName.PUBLISH, body);
        this.author = author;
        this.tags = tags;
    }

    public PublishRequest(String author, String body){
        super( RequestName.PUBLISH + " author " + author,RequestName.PUBLISH, body);
        this.author = author;
        this.tags = null;
    }

    public String getAuthor() {
        return author;
    }

    public List<String> getTags(){
        return tags;
    }



}
