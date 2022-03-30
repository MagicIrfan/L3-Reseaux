package Tools;

public class Options {
    public static String getOptions() {
        return """
                Options :\s
                P - Publier un message\s
                I - Recevoir les id de message
                M - Recevoir un message
                R - Répondre à un message
                S - Republier un message
                A - S'abonner à un utilisateur
                B - Se désabonner à un utilisateur
                C - Afficher les messages contenues dans la boîte de réception
                F - Afficher les utilisateurs les plus populaires
                Q - Quitter""";
    }
}
