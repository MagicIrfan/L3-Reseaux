package process.request;
import process.Process;
import sendable.Sendable;
import response.Response;
import server.data.Database;

import java.io.IOException;
//CLASSE ABSTRAITE TRAITANT LA REQUETE POUR ENVOYER LA REPONSE AU CLIENT
public abstract class ProcessRequest implements Process {

    protected Database database;

    public ProcessRequest(Database database){
        this.database = database;
    }


    abstract public Response getResponse(Sendable receiver);


}
