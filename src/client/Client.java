package client;

import stream.Stream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public abstract class Client {

    protected final BufferedReader reader;
    protected Stream stream;

    public Client() throws IOException {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        InetAddress host = InetAddress.getByName("localhost");
        Socket socket = new Socket(host, 12345);
        this.stream = new Stream(socket);
    }

    public abstract void compute();

}
