package trial.http;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import okhttp3.Response;

import org.junit.BeforeClass;
import org.junit.Test;

import trial.http.attr.OpenWeatherMapApiAttribute;
import trial.http.ssl.RestSecureApiExecutorTest;

/**
 * RestApiExecutorのテストクラス.
 *
 * @author nino
 */
public class RestApiExecutorTest {

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
    public void testGet() throws Exception {

        Map<String, String> params = new HashMap<>();
        params.put("units", "metric"); // 「摂氏」で固定
        params.put("lon", "135");
        params.put("lat", "35");
        params.put("appid", prop.getProperty("api.key.openweathermap"));

        RestApiExecutor target = new RestApiExecutor(new OpenWeatherMapApiAttribute());
        Response response = target.get(null, params, false);

        int statusCode = response.code();
        assertThat(statusCode, is(200));

        String responseBody = response.body().string();
        System.out.println("responseBody : " + responseBody);
    }
}
