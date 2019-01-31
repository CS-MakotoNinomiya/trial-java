package trial.http.request;

/**
 * リクルート用リクエスト管理クラス.
 * 
 * @author nino
 */
public class ReqruitReqestManager extends RequestManager {

    private static final String HOSTNAME = "webservice.recruit.co.jp";

    private static final String SEGMENT = "hotpepper/gourmet/v1";

    private static final String FILE_PATH_CER = "x.509.cer/recruit.base64.encoded.x.509.cer";

    /**
     * コンストラクタ.
     */
    public ReqruitReqestManager() {
        super();
    }

    @Override
    protected String getCertificatePath() {
        return FILE_PATH_CER;
    }

    @Override
    public String getHostname() {
        return HOSTNAME;
    }

    @Override
    public String getSegment() {
        return SEGMENT;
    }
}
