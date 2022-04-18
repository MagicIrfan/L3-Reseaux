package process;

import response.Response;
import sendable.Sendable;

import java.io.IOException;
//INTERFACE TRAITANT LA REQUETE OU LE FLUX POUR ENVOYER LA REPONSE AU CLIENT
public interface Process {
    Response getResponse(Sendable sendable ) throws IOException, InterruptedException;
}
