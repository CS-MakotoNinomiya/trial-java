package trial.http.ssl;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.CacheControl;
import okhttp3.CertificatePinner;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import trial.http.RestApiExecutor;

/**
 * REST API (SSL) 実行クラス.
 *
 * @author nino
 */
public class RestSecureApiExecutor extends RestApiExecutor {

    private CertificateManager certMgr;

    /**
     * コンストラクタ.
     *
     * @param apiAttr セキュアAPI属性
     */
    public RestSecureApiExecutor(SecureApiAttribute apiAttr) {
        super(apiAttr);
        this.certMgr = apiAttr.getCertMgr();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Response get(Map<String, String> header, Map<String, String> param, boolean isValidCache) throws IOException {
        // URLの組み立て
        HttpUrl.Builder urlBuilder = createUrlBuilder(ProtocolType.Secure, param);
        // HTTPヘッダの組み立て
        Request.Builder requestBuilder = createRequestBuilder(header);
        // キャッシュ使用有無
        if (!isValidCache) {
            requestBuilder.cacheControl(new CacheControl.Builder().noCache().noStore().maxAge(0, TimeUnit.SECONDS).build());
        }
        // リクエストの組み立て
        Request request = requestBuilder.url(urlBuilder.build()).build();
        // キャッシュ使用有無
        OkHttpClient.Builder clientBuilder = createClientBuilder(isValidCache);

        addCertificatePinner(clientBuilder, certMgr.getCertificates());
        addSslSocketFactory(clientBuilder, certMgr.getTrustManager());

        // API実行
        return clientBuilder.build().newCall(request).execute();
    }

    /**
     * builder に CertificatePinner を追加します.
     *
     * @param builder OkHttpClient.Builderオブジェクト
     * @param certificates 証明書情報
     */
    private void addCertificatePinner(OkHttpClient.Builder builder, Certificate[] certificates) {
        CertificatePinner certificatePinner = new CertificatePinner.Builder()
                .add(this.hostname, CertificatePinner.pin(certificates[0]))
                .build();
        builder.certificatePinner(certificatePinner);
    }

    /**
     * builder に SslSocketFactory を追加します.
     *
     * @param builder OkHttpClient.Builderオブジェクト
     * @param trustManager TrustManager
     */
    private void addSslSocketFactory(OkHttpClient.Builder builder, X509TrustManager trustManager) {
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[] { trustManager }, null);
            builder.sslSocketFactory(sslContext.getSocketFactory(), trustManager);
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            throw new RuntimeException("____ failed to create ssl socket factory.", e);
        }
    }
}
