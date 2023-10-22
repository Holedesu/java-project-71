package hexlet.code.formatter;

import java.util.ArrayList;
import java.util.Map;

public class Plain {
    public static String format(Map<String, Object> finalMap, Map<String, Object> map1, Map<String, Object> map2) {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, Object> entry : finalMap.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            Object complexValue = prepareValues(value);
            Object complexmap1 = prepareValues(map1.get(key));
            Object complexmap2 = prepareValues(map2.get(key));

            if (value == null) {
                if (map1.get(key) != null && map2.get(key) == null) {
                    result.append("Property '" + key
                            + "' was updated. From " + complexmap1 + " to " + complexValue + "\n");
                } else if (map1.get(key) == null && map2.get(key) != null) {
                    result.append("Property '" + key + "' was added with value: " + complexValue + "\n");
                }
            } else if (map1.containsKey(key) && map2.containsKey(key)) {
                if (value.equals(map1.get(key)) != value.equals(map2.get(key))) {
                    result.append("Property '" + key + "' was updated. From "
                            +  complexmap1 + " to " +  complexmap2 + "\n");
                }

            } else if (!map1.containsKey(key) && map2.containsKey(key)) {
                result.append("Property '" + key + "' was added with value: " + complexValue + "\n");
            } else if (map1.containsKey(key) && !map2.containsKey(key)) {
                result.append("Property '" + key + "' was removed" + "\n");
            }
        }
        return result.toString().trim();
    }
    private static String prepareValues(Object value) {

        if (value instanceof Map || value instanceof ArrayList) {
            return "[complex value]";
        } else if (value == null) {
            return null;
        } else if (value instanceof String) {
            return "'" + value + "'";
        } else {
            return value.toString();
        }
    }
}
