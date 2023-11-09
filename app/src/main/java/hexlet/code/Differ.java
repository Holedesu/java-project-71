package hexlet.code;

import java.nio.file.Files;
import java.util.Map;

import java.nio.file.Path;
import java.nio.file.Paths;

import static hexlet.code.Differance.makeDifference;


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

        Map<String, KeyStatus> finalMap = makeDifference(map1, map2);


        return Formatter.chooseFormatter(finalMap, format);
    }

    public static String getExtension(String file1) {
        String[] fileParts;
        String delimeter = "\\.";
        fileParts = file1.split(delimeter);
        String extension = fileParts[fileParts.length - 1];
        return extension;
    }

    public static String generate(String filePath1, String filePath2) throws Exception {
        String format = "stylish";
        return generate(filePath1, filePath2, format);
    }
}
