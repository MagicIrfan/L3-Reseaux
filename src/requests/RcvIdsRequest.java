package requests;

import requests.name.RequestName;

public class RcvIdsRequest extends Request{

    private String author;
    private String tags;
    private long since;
    private long limit;

    public RcvIdsRequest(String author, String tags, long since, long limit) {
        super("RCV_IDS"/* + ((author.isEmpty()) ? "" : " author:" + author) + (tags.isEmpty()) ? "" : " tag:" + tags + (Long.valueOf(since) == null) ? "" : " since:" + since + (Long.valueOf(limit) == null) ? "" : " limit:" + limit*/, RequestName.RCV_IDS);
        this.author = author;
        this.tags = tags;
        this.since = since;
        this.limit = limit;

    }

    public String getAuthor(){
        return author;
    }

    public String getTags(){
        return tags;
    }

    public long getSince(){
        return since;
    }

    public long getLimit(){
        return limit;
    }
}
