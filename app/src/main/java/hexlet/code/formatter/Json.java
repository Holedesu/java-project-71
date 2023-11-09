package hexlet.code.formatter;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.KeyStatus;

import java.util.Map;

public class Json {
    public static String format(Map<String, KeyStatus> finalMap) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writeValueAsString(finalMap);
        return result;
    }
}
