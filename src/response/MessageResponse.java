package response;

public class MessageResponse extends Response {
    public MessageResponse(String body){
        super("MSG", body);
    }
}
