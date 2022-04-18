package action.client;

import response.Response;
import sendable.Sendable;
import sendable.requests.FamousUserRequest;
import stream.Stream;

import java.io.IOException;

public class FamousUserAction extends ClientAction{

    private String userName;
    public FamousUserAction(Stream stream, String userName) throws IOException {
        super(stream);
        this.userName = userName;
    }

    @Override
    public void doAction() throws IOException, ClassNotFoundException {
        Sendable request = new FamousUserRequest(userName);
        stream.writeData(request);
        System.out.println("Requête envoyée : " + request);
        Response response = (Response) stream.getData();
        System.out.println(response);
    }
}
