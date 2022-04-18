package sendable.requests;

import sendable.requests.name.RequestName;

public class PopularKeyWordsRequest extends Request{
    public PopularKeyWordsRequest(String author) {
        super("POPULAR_KEY_WORDS", RequestName.POPULAR_KEY_WORDS,author);
    }
}
