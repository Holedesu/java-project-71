package hexlet.code.formatter;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class Json {

    public static String format(Map<String, Object> finalMap, Map<String, Object> map1,
                                Map<String, Object> map2) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writeValueAsString(finalMap);
        return result;
    }
}
