package process.request;
import process.Process;
import sendable.Sendable;
import sendable.requests.Request;
import response.Response;
import server.Database;

public abstract class ProcessRequest implements Process {

    protected Database database;

    public ProcessRequest(Database database){
        this.database = database;
    }


    abstract public Response getResponse(Sendable receiver);


}
