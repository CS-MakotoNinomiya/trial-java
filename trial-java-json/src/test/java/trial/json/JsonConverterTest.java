package trial.json;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import org.junit.Test;

/**
 * TrialJsonクラスのテストクラス.
 *
 * @author nino
 */
public class JsonConverterTest {

    /**
     * convertToMapメソッドのテスト.
     * <pre>
     * JSON文字列をMapオブジェクトに変換するメソッドをテストします。
     * </pre>
     *
     * @throws Exception テストに失敗した場合
     */
    @Test
    public void testConvertToMap() throws Exception {
        try {
            String jsonStr = readJsonFileForTest();
            JsonConverter<?> target = new JsonConverter<>();

            Map<String, Object> jsonMap = target.convertToMap(jsonStr);
            assertThat(jsonMap, is(notNullValue()));

            @SuppressWarnings("unchecked")
            List<Integer> list = (List<Integer>) jsonMap.get("arrayVal");
            assertThat(list.size(), is(3));

            assertThat(jsonMap.get("booleanVal"), is(true));
            assertThat(jsonMap.get("colorVal"), is("#82b92c"));
            assertThat(jsonMap.get("nullVal"), is(nullValue()));
            assertThat(jsonMap.get("numberVal"), is(new Integer(123)));

            @SuppressWarnings("unchecked")
            Map<String, Object> obj = (Map<String, Object>) jsonMap.get("objectVal");
            assertThat(obj.get("aa"), is("bb"));
            assertThat(obj.get("cc"), is("dd"));
            assertThat(obj.get("ee"), is("ff"));

            assertThat(jsonMap.get("stringVal"), is("Hello World"));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * convertToDtoメソッドのテスト.
     * <pre>
     * JSON文字列を独自クラスのオブジェクトに変換するメソッドをテストします。
     * </pre>
     *
     * @throws Exception テストに失敗した場合
     */
    @Test
    public void testConvertToDto() throws Exception {
        try {
            String jsonStr = readJsonFileForTest();
            JsonConverter<TrialRootDto> target = new JsonConverter<>();

            TrialRootDto dto = target.convertToDto(jsonStr, TrialRootDto.class);
            assertThat(dto, is(notNullValue()));
            assertThat(dto.getList().size(), is(3));
            assertThat(dto.getBooleanVal(), is(true));
            assertThat(dto.getColorVal(), is("#82b92c"));
            assertThat(dto.getNullVal(), is(nullValue()));
            assertThat(dto.getNumberVal(), is(123));
            assertThat(dto.getObjectVal().getAa(), is("bb"));
            assertThat(dto.getObjectVal().getCc(), is("dd"));
            assertThat(dto.getObjectVal().getEe(), is("ff"));
            assertThat(dto.getStringVal(), is("Hello World"));

            String str = target.convertToStr(dto);
            System.out.println(str);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * JSONファイルを読み込みます.
     * 
     * @return JSON文字列
     * @throws IOException 処理に失敗した場合
     */
    private String readJsonFileForTest() throws IOException {
        InputStream is = ClassLoader.getSystemResourceAsStream("sample.json");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
            }
            return sb.toString();
        }
    }
}
