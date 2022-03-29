package threads;

import Tools.Options;
import message.Notification;
import response.Response;
import stream.Stream;

import java.io.IOException;
import java.util.Deque;
import java.util.Queue;

public class DataReceiver implements Runnable {

    private final Stream stream;
    public DataReceiver(Stream stream){
        this.stream = stream;
    }


    @Override
    public void run() {
        while(true){
            try {
                Object obj = stream.getData();
                if(obj instanceof Notification notification){
                    System.out.println(notification);
                }

                if(obj instanceof Response response){
                    System.out.println(response);
                    System.out.println(Options.getOptions());
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
