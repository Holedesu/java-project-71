package hexlet.code;

import java.nio.file.Files;
import java.util.Map;
import java.util.TreeMap;

import java.nio.file.Path;
import java.nio.file.Paths;


public class Differ {
    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        // Build a route to file
        String baseRoute = "src/test/resources/fixtures/";
        Path path1 = Paths.get(baseRoute + filePath1).toAbsolutePath();
        Path path2 = Paths.get(baseRoute + filePath2).toAbsolutePath();
        // Parsing part start
        String jsonString1 = Files.readString(path1);
        String jsonString2 = Files.readString(path2);

        Map<String, Object> map1 = Parser.parse(getExtension(filePath1), jsonString1);
        Map<String, Object> map2 = Parser.parse(getExtension(filePath2), jsonString2);

        Map<String, Object> finalMap = new TreeMap<>();
        finalMap.putAll(map1);
        finalMap.putAll(map2);

        var result = new StringBuilder();

        result.append("{");
        result.append("\n");
        for (Map.Entry<String, Object> entry : finalMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue().toString();
            if (value.equals(map1.get(key)) && value.equals(map2.get(key))) {
                result.append("    " + key + ": " + value + "\n");
            } else if (map1.containsKey(key) && !map2.containsKey(key)) {
                result.append("  + " + key + ": " + map1.get(key) + "\n");
            } else if (!map1.containsKey(key) && map2.containsKey(key)) {
                result.append("  - " + key + ": " + map2.get(key) + "\n");
            } else if (!map1.get(key).equals(map2.get(key))) {
                result.append("  - " + key + ": " + map2.get(key) + "\n");
                result.append("  + " + key + ": " + map1.get(key) + "\n");
            }
        }
        result.append("}");
        System.out.println(result);
        return result.toString();
    }

    public static String getExtension(String file1) {
        String[] fileParts;
        String delimeter = "\\.";
        fileParts = file1.split(delimeter);
        String extension = fileParts[fileParts.length - 1];
        return extension;
    }
}
