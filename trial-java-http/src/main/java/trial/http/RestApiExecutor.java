package trial.http;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * REST API 実行クラス.
 *
 * @author nino
 */
public class RestApiExecutor {

    /**
     * プロトコルタイプ.
     *
     * @author nino
     */
    public enum ProtocolType {
        /** 通常. */
        Normal("http"),
        /** SSL. */
        Secure("https"),;

        private String type;

        private ProtocolType(String type) {
            this.type = type;
        }

        public String getValue() {
            return this.type;
        }
    }

    /** ホスト名. */
    protected String hostname;
    /** セグメント. */
    protected String segment;

    /**
     * コンストラクタ.
     *
     * @param apiAttr API属性
     */
    public RestApiExecutor(ApiAttribute apiAttr) {
        // ホスト名、セグメント
        this.hostname = apiAttr.getHostname();
        this.segment = apiAttr.getSegment();
    }

    /**
     * REST API (get) を実行します.
     *
     * @param header HTTPヘッダパラメータ
     * @param param クエリパラメータ
     * @param isValidCache キャッシュ使用（true：使用する、false：使用しない）
     * @return レスポンス
     * @throws IOException 処理に失敗した場合
     */
    public Response get(Map<String, String> header, Map<String, String> param, boolean isValidCache) throws IOException {
        // URLの組み立て
        HttpUrl.Builder urlBuilder = createUrlBuilder(ProtocolType.Normal, param);
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
        // API実行
        return clientBuilder.build().newCall(request).execute();
    }

    /**
     * URLを組み立てます.
     *
     * @param type ProtocolType
     * @param param パラメータ
     * @return HttpUrl.Builderオブジェクト
     */
    protected HttpUrl.Builder createUrlBuilder(ProtocolType type, Map<String, String> param) {
        // URLビルダー
        HttpUrl.Builder builder = new HttpUrl.Builder();
        // スキーマ(http or https)
        builder.scheme(type.getValue());
        // ホスト名
        builder.host(this.hostname);
        // セグメント
        builder.addPathSegments(this.segment);
        if (param != null) {
            // クエリパラメータ
            param.forEach(builder::addQueryParameter);
        }
        return builder;
    }

    /**
     * HTTPヘッダーを組み立てます.
     *
     * @param param パラメータ
     * @return Request.Builderオブジェクト
     */
    protected Request.Builder createRequestBuilder(Map<String, String> param) {
        Request.Builder builder = new Request.Builder();
        builder.addHeader("Content-Type", "application/json");
        if (param != null) {
            param.forEach(builder::addHeader);
        }
        return builder;
    }

    /**
     * HTTPクライアントを組み立てます.
     *
     * @param isValidCache キャッシュ使用有無
     * @return OkHttpClient.Builderオブジェクト
     */
    protected OkHttpClient.Builder createClientBuilder(boolean isValidCache) {
        // キャッシュ使用有無
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (!isValidCache) {
            builder.cache(null);
        }
        return builder;
    }
}
