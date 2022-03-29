package client.action;

import client.User;
import sendable.Sendable;
import stream.Stream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public abstract class ClientAction {

    protected Stream stream;
    protected String userName;
    protected final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public ClientAction(Stream stream,String userName) throws IOException {
        this.stream = stream;
        this.userName = userName;
    }


    public abstract void doAction() throws IOException, ClassNotFoundException;
}
