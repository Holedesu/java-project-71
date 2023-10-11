package hexlet.code;

import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;


public class Parser {
    public static Map<String, Object> parse(String extension, String content) throws Exception {

        ObjectMapper jsonMapper = new ObjectMapper();
        ObjectMapper yamlMapper = new ObjectMapper(new YAMLFactory());

        switch (extension) {
            case "json":
                return jsonMapper.readValue(content, Map.class);
            case "yml":
            case "yaml":
                return yamlMapper.readValue(content, Map.class);
            default:
                throw new RuntimeException("No parser for extension: " + extension);
        }
    }
}
