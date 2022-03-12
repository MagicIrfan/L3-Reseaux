package process.flux;

import client.User;
import process.Process;
import response.Response;
import sendable.Sendable;

import java.util.*;

public abstract class ProcessFlux implements Process {

    protected User user;
    protected Map<User, List<User>> subscribers;

    public ProcessFlux(Map<User, List<User>> subscribers, User user){
        this.subscribers = subscribers;
        this.user = user;
    }

    @Override
    abstract public Response getResponse(Sendable sendable);
}
