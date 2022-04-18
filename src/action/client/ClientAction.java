package action.client;

import action.Actionable;
import client.User;
import sendable.Sendable;
import stream.Stream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
//CLASSE ABSTRAITE REPRESENTANT L'ACTION DU CLIENT LORSQU'IL VEUT ENVOYER UNE REQUETE
public abstract class ClientAction implements Actionable {

    protected final Stream stream;
    protected final BufferedReader reader;

    public ClientAction(Stream stream) throws IOException {
        this.stream = stream;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }


    public abstract void doAction() throws IOException, ClassNotFoundException;
}
