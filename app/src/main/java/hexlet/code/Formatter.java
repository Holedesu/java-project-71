package hexlet.code;

import hexlet.code.formatter.Plain;
import hexlet.code.formatter.Stylish;

import java.util.Map;

public class Formatter {
    public static String chooseFormatter(Map<String, Object> finalMap, Map<String, Object> map1,
                                         Map<String, Object> map2, String formatter) {
        formatter = formatter.toLowerCase();
        switch (formatter) {
            case "stylish":
                return Stylish.format(finalMap, map1, map2);
            case "plain":
                return Plain.format(finalMap, map1, map2);
            default:
                throw new RuntimeException("No formatter for extension: " + formatter);
        }
    }
}
