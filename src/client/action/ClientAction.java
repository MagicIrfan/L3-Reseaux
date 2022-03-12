package client.action;

import client.User;
import packet.Packet;
import sendable.Sendable;
import stream.Stream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public abstract class ClientAction {

    protected Stream stream;
    protected User user;
    protected final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public ClientAction(Stream stream,User user) throws IOException {
        this.stream = stream;
        this.user = user;
    }

    public void sendPacket(Sendable sendable) throws IOException {
        Packet packet = new Packet(user,sendable);
        stream.writeData(packet);
    }

    public abstract void doAction() throws IOException, ClassNotFoundException;
}
