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

    private ObjectMapper objectMapper;

    /**
     * コンストラクタ.
     */
    public JsonConverter() {
        this.objectMapper = new ObjectMapper();
    }

    /**
     * JSON文字列からMapオブジェクトに変換します.
     *
     * @param jsonStr JSON文字列
     * @return Mapオブジェクト
     * @throws IOException 変換に失敗した場合
     */
    public Map<String, Object> convertToMap(String jsonStr) throws IOException {
        // 引数チェック
        validateJsonStr(jsonStr);

        return this.objectMapper.readValue(jsonStr, new TypeReference<Map<String, Object>>() {});
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
        // 引数チェック
        validateJsonStr(jsonStr);
        validateClass(clz);

        return this.objectMapper.readValue(jsonStr, clz);
    }

    /**
     * MapオブジェクトからJSON文字列へ変換します.
     *
     * @param map Mapオブジェクト
     * @return JSON文字列
     * @throws IOException 変換に失敗した場合
     */
    public String convertToStr(Map<String, Object> map) throws IOException {
        // 引数チェック
        validateMap(map);

        return this.objectMapper.writeValueAsString(map);
    }

    /**
     * 自作オブジェクトからJSON文字列へ変換します.
     *
     * @param obj 自作オブジェクト
     * @return JSON文字列
     * @throws IOException 変換に失敗した場合
     */
    public String convertToStr(T obj) throws IOException {
        // 引数チェック
        validateObj(obj);

        return this.objectMapper.writeValueAsString(obj);
    }

    /**
     * JSON文字列のチェック.
     *
     * @param jsonStr JSON文字列
     */
    private void validateJsonStr(String jsonStr) {
        if (jsonStr == null) {
            throw new IllegalArgumentException("jsonStr is null.");
        }
        if (jsonStr.isEmpty()) {
            throw new IllegalArgumentException("jsonStr is empty.");
        }
    }

    /**
     * クラスオブジェクトのチェック.
     *
     * @param clz クラスオブジェクト
     */
    private void validateClass(Class<T> clz) {
        if (clz == null) {
            throw new IllegalArgumentException("clz is null.");
        }
    }

    /**
     * Mapオブジェクトのチェック.
     *
     * @param map Mapオブジェクト
     */
    private void validateMap(Map<String, Object> map) {
        if (map == null) {
            throw new IllegalArgumentException("map is null.");
        }
    }

    /**
     * 自作オブジェクトのチェック.
     *
     * @param obj 自作オブジェクト
     */
    private void validateObj(T obj) {
        if (obj == null) {
            throw new IllegalArgumentException("obj is null.");
        }
    }
}
