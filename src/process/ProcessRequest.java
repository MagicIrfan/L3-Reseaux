package process;

import requests.Request;
import response.Response;
import server.Database;

public abstract class ProcessRequest {

    protected Database database;

    public ProcessRequest(Database database){
        this.database = database;
    }


    abstract public Response getResponse(Request receiver);


}
