package Tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Tags {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static List<String> getTags() throws IOException {
        List<String> tags = new ArrayList<>();
        String temp = "";

        do{
            String tag = "";
            System.out.println("Saisissez un tag (ne rien saisir si vous voulez finir la saisie)");
            System.out.print("#");

            temp = reader.readLine();
            if(!temp.isEmpty()){
                tag= "#" + temp;
                tags.add(tag);
            }

        }
        while(!temp.isEmpty());
        return tags;
    }
}
