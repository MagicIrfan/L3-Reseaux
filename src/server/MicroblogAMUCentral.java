package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.*;

public class MicroblogAMUCentral extends Server{


    public MicroblogAMUCentral() throws IOException {
        super();
    }

    public static void main(String[] arg) throws IOException {
        Server server = new MicroblogAMUCentral();
        while(true){
            server.execute();
        }
    }

    @Override
    public void execute() throws IOException {
        executorService.execute(new SocketHandler(serverSocket.accept()));
    }
}
