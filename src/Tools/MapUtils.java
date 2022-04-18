package Tools;

import client.User;

import java.util.*;
import java.util.stream.*;

public class MapUtils {

    public static Map<User, Integer> sortFamousByValue(Map<User, Integer> unsortMap)
    {
        List<Map.Entry<User, Integer>> list = new LinkedList<>(unsortMap.entrySet());

        list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()) == 0
                ? o2.getKey().compareTo(o1.getKey())
                : o2.getValue().compareTo(o1.getValue()));
        return list.stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> b, LinkedHashMap::new));

    }

    public static Map<Long, Integer> sortRepublishedByValue(Map<Long, Integer> unsortMap)
    {
        List<Map.Entry<Long, Integer>> list = new LinkedList<>(unsortMap.entrySet());

        // Sorting the list based on values
        list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()) == 0
                ? o2.getKey().compareTo(o1.getKey())
                : o2.getValue().compareTo(o1.getValue()));
        return list.stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> b, LinkedHashMap::new));

    }
}
