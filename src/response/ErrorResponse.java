package response;

public class ErrorResponse extends Response {
    public ErrorResponse(String body){
        super("ERROR",body);
    }
}
