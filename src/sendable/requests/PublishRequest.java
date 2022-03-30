package sendable.requests;

import sendable.requests.name.RequestName;

import java.util.*;

public class PublishRequest extends Request {

    private String author;
    private List<String> tags;

    public PublishRequest(String author, String body, List<String> tags){
        super( RequestName.PUBLISH + " author " + author,RequestName.PUBLISH, body,author);
        this.tags = tags;
    }

    public PublishRequest(String author, String body){
        super( RequestName.PUBLISH + " author " + author,RequestName.PUBLISH, body,author);
        this.tags =  new ArrayList<>();
    }

    public List<String> getTags(){
        return tags;
    }



}
