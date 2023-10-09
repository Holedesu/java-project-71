package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;
import java.util.TreeMap;
import java.util.HashMap;

import java.nio.file.Path;
import java.nio.file.Paths;


public class Differ {
    public static String generate(String filePath1, String filePath2) throws IOException {
        // Build a route to file
        String baseRoute = "src/test/resources/fixtures/";
//        Path fullPath1 = Paths.get(filePath1).toAbsolutePath().normalize();
//        Path fullPath2 = Paths.get(filePath2).toAbsolutePath().normalize();
        Path path1 = Paths.get(baseRoute + filePath1).toAbsolutePath();
        Path path2 = Paths.get(baseRoute + filePath2).toAbsolutePath();
        // Parsing part start
        String jsonString1 = new String(Files.readAllBytes(path1));
        String jsonString2 = new String(Files.readAllBytes(path2));
//        String jsonString1 = Files.readString(fullPath1);
//        String jsonString2 = Files.readString(fullPath2);

        jsonString1 = jsonString1.replaceAll(",", "");
        jsonString1 = jsonString1.replaceAll("\"", "");
        jsonString1 = jsonString1.substring(1, jsonString1.length() - 1);
        jsonString1 = jsonString1.trim();

        jsonString2 = jsonString2.replaceAll(",", "");
        jsonString2 = jsonString2.replaceAll("\"", "");
        jsonString2 = jsonString2.substring(1, jsonString2.length() - 1);
        jsonString2 = jsonString2.trim();

        String[] lines1 = jsonString1.split("\n");
        String[] lines2 = jsonString2.split("\n");
        // Parsing part end

        // Prepare data for comparing
        Map<String, String> map1 = new HashMap<>();
        Map<String, String> map2 = new HashMap<>();
        Map<String, String> finalMap = new TreeMap<>();
        // first file
        for (String line : lines1) {
            line = line.trim();
            String[] lineSplit1 = line.split(": ");
            map1.put(lineSplit1[0], lineSplit1[1]);
        }
        // Second file
        for (String line2 : lines2) {
            line2 = line2.trim();
            String[] lineSplit2 = line2.split(": ");
            map2.put(lineSplit2[0], lineSplit2[1]);
        }
        // Add to tree that filter it
        finalMap.putAll(map1);
        finalMap.putAll(map2);

        //Making string that returns difference
        var result = new StringBuilder();

        result.append("{");
        result.append("\n");
        //Comparing data files
        for (Map.Entry<String, String> entry : finalMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
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

    public static void main(String[] args) {
        try {
            generate("filepath1.json", "filepath2.json");
        }    catch (IOException e) {
            e.printStackTrace();
        }
    }
}
