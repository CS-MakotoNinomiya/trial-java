package trial.http;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Response;

import org.junit.Test;

import trial.http.attr.OpenWeatherMapApiAttribute;

/**
 * RestApiExecutorのテストクラス.
 *
 * @author nino
 */
public class RestApiExecutorTest {

    @Test
    public void testGet() throws Exception {

        Map<String, String> params = new HashMap<>();
        params.put("units", "metric"); // 「摂氏」で固定
        params.put("lon", "135");
        params.put("lat", "35");
        params.put("appid", "9e156142cc07a547fe564a2e8a82cf9c");

        RestApiExecutor target = new RestApiExecutor(new OpenWeatherMapApiAttribute());
        Response response = target.get(null, params, false);

        int statusCode = response.code();
        assertThat(statusCode, is(200));

        String responseBody = response.body().string();
        System.out.println("responseBody : " + responseBody);
    }
}
