package trial.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

/**
 * JSON文字列⇔オブジェクト 変換クラス.
 * 
 * @author nino
 * @param <T> 変換対象のクラス
 */
public class JsonConverter<T> {

    /**
     * JSON文字列からMapオブジェクトに変換します.
     * 
     * @param jsonStr JSON文字列
     * @return Mapオブジェクト
     * @throws IOException 変換に失敗した場合
     */
    public Map<String, Object> convertToMap(String jsonStr) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonStr, new TypeReference<Map<String, Object>>() {});
    }

    /**
     * JSON文字列から自作オブジェクトへ変換します.
     * 
     * @param jsonStr JSON文字列
     * @param clz 自作クラス
     * @return 自作オブジェクト
     * @throws IOException 変換に失敗した場合
     */
    public T convertToDto(String jsonStr, Class<T> clz) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonStr, clz);
    }

    /**
     * MapオブジェクトからJSON文字列へ変換します.
     * 
     * @param map Mapオブジェクト
     * @return JSON文字列
     * @throws IOException 変換に失敗した場合
     */
    public String convertToStr(Map<String, Object> map) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(map);
    }

    /**
     * 自作オブジェクトからJSON文字列へ変換します.
     * 
     * @param obj 自作オブジェクト
     * @return JSON文字列
     * @throws IOException 変換に失敗した場合
     */
    public String convertToStr(T obj) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
}
