package Tools;

public class CharVerificator {

    public static boolean tagIsValid(String value){
        return value.charAt(0) == '#';
    }

    public static boolean nameIsValid(String value){
        return value.charAt(0) == '@';
    }

}
