package action.client;

import Tools.CharVerificator;
import action.client.ClientAction;
import response.Response;
import sendable.requests.RcvIdsRequest;
import sendable.requests.Request;
import stream.Stream;

import java.io.IOException;

public class RcvIdsAction extends ClientAction {


    public RcvIdsAction(Stream stream) throws IOException {
        super(stream);
    }

    @Override
    public void doAction() throws IOException, ClassNotFoundException {
        System.out.println("Saisissez l'auteur des messages : (ne rien saisir sinon)");
        String author = reader.readLine();

        System.out.println("Saisir un tag (ne rien saisir sinon)");
        String temp = reader.readLine();

        System.out.println("Saisir l'id du message où les messages ont été publiés après cet id");
        String strSince = reader.readLine();

        System.out.println("Nombre maximum d'identifiants renvoyés : ");
        String strLimit = reader.readLine();

        long since = 0,limit = Long.MAX_VALUE;

        if(!author.isEmpty()){
            if(!CharVerificator.nameIsValid(author))
                author = null;
        }
        if(!temp.isEmpty()){
            if(!CharVerificator.tagIsValid(temp))
                temp = null;
        }
        if(!strSince.isEmpty()){
            if(!Float.isNaN(Float.parseFloat(strSince))){
                long tempsince = Long.parseLong(strSince);
                since = tempsince < 0 ? since : tempsince;
            }

        }

        if(!strLimit.isEmpty()){
            if(!Float.isNaN(Float.parseFloat(strLimit))){
                long templimit = Long.parseLong(strLimit);
                limit = templimit < 0 ? limit : templimit;
            }
        }


        Request request = new RcvIdsRequest(author,temp,since,limit);
        stream.writeData(request);
        System.out.println("Requete envoyée : " + request);
        Response response = (Response) stream.getData();
        System.out.println(response);
    }
}
