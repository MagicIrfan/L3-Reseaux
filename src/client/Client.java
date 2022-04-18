package client;

import Tools.Name;
import stream.Stream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
//CLASSE ABSTRAITE REPRESENTANT LE CLIENT
public abstract class Client  {

    protected final BufferedReader reader;
    protected final Stream stream;
    protected Socket socket;
    protected String userName;

    public Client() throws IOException {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        InetAddress host = InetAddress.getByName("localhost");
        this.socket = new Socket(host, 12345);
        this.stream = new Stream(socket);
        this.userName = Name.getName();
    }

    public abstract void compute();

    public Socket getSocket(){ return socket; }

}
