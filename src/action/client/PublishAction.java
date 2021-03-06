package action.client;

import action.client.ClientAction;
import response.Response;
import sendable.requests.PublishRequest;
import sendable.requests.Request;
import stream.Stream;

import java.io.IOException;
import java.util.List;

import static Tools.Tags.getTags;

public class PublishAction extends ClientAction {


    private String userName;
    public PublishAction(Stream stream, String user) throws IOException {
        super(stream);
        this.userName = user;
    }

    @Override
    public void doAction() throws IOException, ClassNotFoundException {
        System.out.println("Saisissez un message (pas plus de 255 caractères)");
        String body = reader.readLine();
        List<String> tags = getTags();
        Request request = new PublishRequest(userName,body,tags);
        stream.writeData(request);
        System.out.println("Requete envoyée : " + request);
        Response response = (Response) stream.getData();
        System.out.println(response);

    }
}
