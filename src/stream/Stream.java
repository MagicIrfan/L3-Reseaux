package stream;

import java.io.*;
import java.net.*;

public class Stream {

    private final Socket socket;
    private final ObjectOutputStream objectOutputStream;
    private final ObjectInputStream objectInputStream;

    public Stream(Socket socket) throws IOException {
        this.socket = socket;
        this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        this.objectOutputStream.flush();
        this.objectInputStream = new ObjectInputStream(socket.getInputStream());
    }

    public Stream(Stream stream) throws IOException {
        this.socket = stream.getSocket();
        this.objectOutputStream = stream.getObjectOutputStream();
        this.objectOutputStream.flush();
        this.objectInputStream = stream.getObjectInputStream();
    }

    public Object getData() throws IOException, ClassNotFoundException {
        return objectInputStream.readObject();
    }

    public void writeData(Object object) throws IOException {
        objectOutputStream.flush();
        objectOutputStream.writeObject(object);

    }

    public ObjectOutputStream getObjectOutputStream(){
        return objectOutputStream;
    }

    public ObjectInputStream getObjectInputStream(){
        return objectInputStream;
    }

    public OutputStream getOutputStream() throws IOException {
        return socket.getOutputStream();
    }

    public InputStream getInputStream() throws IOException {
        return socket.getInputStream();
    }

    public Socket getSocket(){
        return socket;
    }



}
