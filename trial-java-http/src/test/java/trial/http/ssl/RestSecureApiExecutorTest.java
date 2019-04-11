package trial.http.ssl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import okhttp3.Response;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;

import trial.http.ssl.attr.QiitaApiAttribute;
import trial.http.ssl.attr.ReqruitApiAttribute;

/**
 * RestSecureApiExecutorのテストクラス.
 *
 * @author nino
 */
@FixMethodOrder
public class RestSecureApiExecutorTest {

// CHECKSTYLE:OFF

    private static Properties prop = null;

    /**
     * テスト前処理.
     *
     * @throws IOException 処理に失敗した場合
     */
    @BeforeClass
    public static void beforeClass() throws IOException {
        InputStream in = RestSecureApiExecutorTest.class.getClassLoader().getResourceAsStream("apikey.properties");
        prop = new Properties();
        prop.load(in);
    }

    @Test
    public void test001_GetReqruitApi() throws Exception {
        try {
            ReqruitApiAttribute apiAttr = new ReqruitApiAttribute();
            RestSecureApiExecutor apiExecutor = new RestSecureApiExecutor(apiAttr);

            Map<String, String> param = new HashMap<>();
            param.put("key", prop.getProperty("api.key.reqruit"));
            param.put("large_area", "Z011");
            param.put("format", "json");

            Response response = apiExecutor.get(null, param, true);
            int statusCode = response.code();
            assertThat(statusCode, is(200));

            String responseBody = response.body().string();
            System.out.println("responseBody : " + responseBody);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Test
    public void test002_GetQiitaApi() throws Exception {
        try {
            QiitaApiAttribute apiAttr = new QiitaApiAttribute();
            RestSecureApiExecutor apiExecutor = new RestSecureApiExecutor(apiAttr);

            Map<String, String> header = new HashMap<>();
            header.put("authorization", prop.getProperty("authorization.qiita"));

            Response response = apiExecutor.get(header, null, false);
            int statusCode = response.code();
            assertThat(statusCode, is(200));

            String responseBody = response.body().string();
            ObjectMapper mapper = new ObjectMapper();
            List<Map<String, Object>> list = mapper.readValue(responseBody, new TypeReference<List<Map<String, Object>>>() {});
            list = list.stream().sorted(new Comparator<Map<String, Object>>() {
                @Override
                public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                    return ((String) o1.get("title")).compareTo((String) o2.get("title"));
                }
            }).collect(Collectors.toList());
            System.out.println("||タイトル||いいねの数||更新日時||");
            for (Map<String, Object> map : list) {
                String title = (String) map.get("title");
                String url = (String) map.get("url");
                Integer likesCnt = (Integer) map.get("likes_count");
                String updateDt = (String) map.get("updated_at");
                updateDt = updateDt.substring(0, 10);

                String str = String.format("|[%s|%s]|%d|%s|", title, url, likesCnt, updateDt);
                System.out.println(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
