package hexlet.code;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Plain {

    public static String format(Map<String, Object> finalMap, Map<String, Object> map1, Map<String, Object> map2) {
        StringBuilder result = new StringBuilder();
        System.out.println(finalMap);

        for (Map.Entry<String, Object> entry : finalMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue().toString();
//            if (value == null) {
//                if (map1.containsKey(key) && !map2.containsKey(key)) {
//                    result.append("  + " + key + ": " + null + "\n");
//                } else if (!map1.containsKey(key) && map2.containsKey(key)) {
//                    result.append("  - " + key + ": " + null + "\n");
//            }
//                result.append("    " + key + ": " + null + "\n");
//            }
            if (value.equals(map1.get(key)) && value.equals(map2.get(key))) {
                result.append("    " + key + ": " + value + "\n");
            } else if (map1.containsKey(key) && !map2.containsKey(key)) {
                result.append("  + " + key + ": " + map1.get(key) + "\n");
            } else if (!map1.containsKey(key) && map2.containsKey(key)) {
                result.append("  - " + key + ": " + map2.get(key) + "\n");
            } else if (map1.containsKey(key) || map2.containsKey(key)) {
                result.append("    " + key + ": " + value + "\n");
            } else if (!map1.get(key).equals(map2.get(key))) {
                result.append("  - " + key + ": " + map2.get(key) + "\n");
                result.append("  + " + key + ": " + map1.get(key) + "\n");
            }
        }

        result.append("{");
        result.append("\n");

        result.append("}");
        System.out.println(result);
        return result.toString();
    }
}
