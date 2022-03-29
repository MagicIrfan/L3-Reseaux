package response;

public class ListMsgResponse extends Response{
    public ListMsgResponse(String body) {
        super("LIST_MSG", body);
    }
}
