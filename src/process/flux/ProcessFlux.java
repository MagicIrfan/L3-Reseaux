package process.flux;

import client.User;
import process.Process;
import response.Response;
import sendable.Sendable;
import server.data.Database;

public abstract class ProcessFlux implements Process {

    protected User client;
    protected Database database;

    public ProcessFlux(Database database,User client){
        this.database = database;
        this.client = client;
    }

    @Override
    abstract public Response getResponse(Sendable sendable);
}
