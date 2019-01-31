package trial.http.ssl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Response;

import org.junit.Test;

import trial.http.request.ReqruitReqestManager;

/**
 * RestSecureApiExecutorのテストクラス.
 * 
 * @author nino
 */
public class RestSecureApiExecutorTest {

    @Test
    public void testGet() throws Exception {
        try {
            ReqruitReqestManager reqMgr = new ReqruitReqestManager();
            RestSecureApiExecutor apiExecutor = new RestSecureApiExecutor(reqMgr);

            Map<String, String> param = new HashMap<>();
            param.put("key", "77c7ee6ef935d30d");
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
}
