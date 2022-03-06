package stream;

import java.io.*;
import java.net.*;

public class Stream {

    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    public Stream(Socket socket) throws IOException {
        this.socket = socket;
        this.inputStream = this.socket.getInputStream();
        this.outputStream = this.socket.getOutputStream();
        this.objectOutputStream = new ObjectOutputStream(this.outputStream);
        this.objectInputStream = new ObjectInputStream(this.inputStream);
    }

    public Object getData() throws IOException, ClassNotFoundException {
        return objectInputStream.readObject();
    }

    public void writeData(Object object) throws IOException {
        objectOutputStream.writeObject(object);
    }

    public Socket getSocket(){
        return socket;
    }


}
