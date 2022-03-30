package action.client;

import Tools.Tags;
import action.client.ClientAction;
import sendable.requests.ReplyRequest;
import sendable.requests.Request;
import stream.Stream;

import java.io.IOException;
import java.util.List;

public class ReplyAction extends ClientAction {


    private String userName;
    public ReplyAction(Stream stream, String user) throws IOException {
        super(stream);
        this.userName = user;
    }

    public long processSince(String strSince){
        long since = 0;
        if(!strSince.isEmpty()){
            if(!Float.isNaN(Float.parseFloat(strSince)))
                since = Long.parseLong(strSince);
        }
        return since;
    }


    @Override
    public void doAction() throws IOException, ClassNotFoundException {
        System.out.println("Saisissez l'id du message à répondre");
        String strSince = reader.readLine();
        long since = processSince(strSince);
        System.out.println("Saisissez le message");
        String message = reader.readLine();

        List<String> tags = Tags.getTags();

        Request request = new ReplyRequest(since,message,userName,tags);
        stream.writeData(request);
        System.out.println("Requete envoyée : " + request);
    }
}
