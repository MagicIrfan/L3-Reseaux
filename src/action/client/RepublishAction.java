package action.client;

import action.client.ClientAction;
import response.Response;
import sendable.requests.RepublishRequest;
import sendable.requests.Request;
import stream.Stream;

import java.io.IOException;

public class RepublishAction extends ClientAction {


    private String userName;
    public RepublishAction(Stream stream, String user) throws IOException {
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
        System.out.println("Saisissez l'id du message à républier");
        String strSince = reader.readLine();
        long since = processSince(strSince);

        Request request = new RepublishRequest(since,userName);
        stream.writeData(request);
        System.out.println("Requete envoyée : " + request);
        Response response = (Response) stream.getData();
        System.out.println(response);
    }
}
