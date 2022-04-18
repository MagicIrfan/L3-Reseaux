package response;

import java.io.Serializable;
//CLASSE REPRESENTANT LA REPONSE A LA REQUETE OU FLUX ENVOYEE PAR LE CLIENT
public abstract class Response implements Serializable {
    protected String header;
    protected String body;

    public Response(String header, String body){
        this.header = header;
        this.body = body;
    }

    public String toString(){
        return header + "\r\n" + body + "\r\n";
    }


}
