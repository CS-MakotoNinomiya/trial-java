package trial.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

public class TrialJson<T> {

    public Map<String, Object> convertToMap(String jsonStr) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonStr, new TypeReference<Map<String, Object>>() {});
    }

    public T convertToDto(String jsonStr, Class<T> clz) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonStr, clz);
    }
}
