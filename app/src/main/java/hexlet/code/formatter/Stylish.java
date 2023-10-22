package hexlet.code.formatter;

//import java.io.IOException;
import java.util.Map;
//import java.util.TreeMap;

public class Stylish {
    public static String format(Map<String, Object> finalMap, Map<String, Object> map1, Map<String, Object> map2) {
        StringBuilder result = new StringBuilder();

        result.append("{");
        result.append("\n");
        for (Map.Entry<String, Object> entry : finalMap.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value == null) {
                if (map1.get(key) == null && map2.get(key) == null) {
                    result.append("    " + key + ": " + value + "\n");
                } else if (map1.get(key) != null && map2.get(key) == null) {
                    result.append("  - " + key + ": " + map1.get(key) + "\n");
                    result.append("  + " + key + ": " + null + "\n");
                } else if (map1.get(key) == null && map2.get(key) != null) {
                    result.append("  + " + key + ": " + map2.get(key) + "\n");
                    result.append("  - " + key + ": " + null + "\n");
                }
            } else if (map1.containsKey(key) && map2.containsKey(key)) {
                if (value.equals(map1.get(key)) == value.equals(map2.get(key))) {
                    result.append("    " + key + ": " + value + "\n");
                } else if (value.equals(map1.get(key)) != value.equals(map2.get(key))) {
                    result.append("  - " + key + ": " + map1.get(key) + "\n");
                    result.append("  + " + key + ": " + map2.get(key) + "\n");
                }
            } else if (map1.containsKey(key) && !map2.containsKey(key)) {
                result.append("  - " + key + ": " + value + "\n");
            } else if (!map1.containsKey(key) && map2.containsKey(key)) {
                result.append("  + " + key + ": " + value + "\n");
            }
        }
        result.append("}");
        return result.toString();
    }
}
