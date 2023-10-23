package hexlet.code;

import hexlet.code.formatter.Plain;
import hexlet.code.formatter.Stylish;
import hexlet.code.formatter.Json;

import java.util.Map;

public class Formatter {
    public static String chooseFormatter(Map<String, Object> finalMap, Map<String, Object> map1,
                                         Map<String, Object> map2, String formatter) throws Exception {
        formatter = formatter.toLowerCase();
        switch (formatter) {
            case "json":
                return Json.format(finalMap, map1, map2);
            case "stylish":
                return Stylish.format(finalMap, map1, map2);
            case "plain":
                return Plain.format(finalMap, map1, map2);
            default:
                throw new RuntimeException("No formatter for extension: " + formatter);
        }
    }
}
