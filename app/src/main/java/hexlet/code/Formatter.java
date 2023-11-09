package hexlet.code;

import hexlet.code.formatter.Plain;
import hexlet.code.formatter.Stylish;
import hexlet.code.formatter.Json;

import java.util.Map;

public class Formatter {
    public static String chooseFormatter(Map<String, KeyStatus> finalMap, String formatter) throws Exception {
        formatter = formatter.toLowerCase();
        switch (formatter) {
            case "json":
                return Json.format(finalMap);
            case "stylish":
                return Stylish.format(finalMap);
            case "plain":
                return Plain.format(finalMap);
            default:
                throw new RuntimeException("No formatter for extension: " + formatter);
        }
    }
}
