package sendable.requests;

import sendable.requests.name.RequestName;

public class RcvIdsRequest extends Request{

    private String tags;
    private long since;
    private long limit;

    public RcvIdsRequest(String author, String tags, long since, long limit) {
        super("RCV_IDS" + ((author.isEmpty()) ? "" : " author:" + author)
                + ((tags.isEmpty()) ? "" : " tag:" + tags)
                + ((since == 0) ? "" : " since:" + since)
                + ((limit == Long.MAX_VALUE) ? "" : " limit:" + limit), RequestName.RCV_IDS,author);
        this.tags = tags;
        this.since = since;
        this.limit = limit;

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
