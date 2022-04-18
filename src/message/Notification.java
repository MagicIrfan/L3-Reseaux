package message;

import java.io.Serializable;
//CLASSE REPRESENTANT UNE NOTIFICATION QUI EST ENVOYEE PAR LE SERVEUR
public class Notification implements Serializable {

    private final String body;

    public Notification(String userName){
        this.body = userName + " a publié un message, regardez votre boîte de réception !";
    }

    @Override
    public String toString(){
        return body;
    }
}
