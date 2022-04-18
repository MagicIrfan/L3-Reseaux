package action.server;

import action.Actionable;
import sendable.Sendable;
import server.data.Database;
import stream.Stream;

import java.io.IOException;
//CLASSE ABSTRAITE REPRESENTANT L'ACTION DU SERVEUR LORSQU'IL VEUT ENVOYER UNE REPONSE
public abstract class ServerAction implements Actionable {

    protected final Database database;
    protected Sendable sendable;
    protected final Stream stream;

    public ServerAction(Database database, Sendable sendable, Stream stream){
        this.database = database;
        this.sendable = sendable;
        this.stream = stream;
    }

    @Override
    public abstract void doAction() throws IOException, ClassNotFoundException, InterruptedException;
}
