package Tools;

import java.util.Scanner;

public class Name {
    public static String getName(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Saisissez le pseudo : @");
        String name = "";
        do{
            name = sc.nextLine();
        }
        while(name.isEmpty());

        return "@"+name;
    }
}
