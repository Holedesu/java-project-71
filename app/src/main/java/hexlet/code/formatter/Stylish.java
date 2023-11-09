package hexlet.code.formatter;

import hexlet.code.KeyStatus;
import java.util.Map;


public class Stylish {
    public static String format(Map<String, KeyStatus> finalMap) {
        StringBuilder result = new StringBuilder();

        result.append("{");
        result.append("\n");
        for (Map.Entry<String, KeyStatus> element : finalMap.entrySet()) {

            String status = element.getValue().getStatus();
            String key = element.getKey();
            var value1 = element.getValue().getValue1();
            var value2 = element.getValue().getValue2();

            switch (status) {
                case "deleted" -> result.append("  - ").append(key).append(": ").append(value1).append("\n");
                case "added" -> result.append("  + ").append(key).append(": ").append(value2).append("\n");
                case "unchanged" -> result.append("    ").append(key).append(": ").append(value1).append("\n");
                case "changed" -> result.append("  - ").append(key).append(": ").append(value1).
                        append("\n").append("  + ").append(key).append(": ").append(value2).append("\n");
                default -> {
                    return "Something went wrong for input: " + element.getValue();
                }
            }
        }
        result.append("}");
        return result.toString();
    }
}
