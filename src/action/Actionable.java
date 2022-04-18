package action;

import java.io.IOException;
//INTERFACE REPRESENTANT L'ACTION DU SERVEUR LORSQU'IL VEUT ENVOYER UNE REPONSE
public interface Actionable {
    void doAction() throws IOException, ClassNotFoundException, InterruptedException;
}
