package trial.http.ssl.attr;

import trial.http.ssl.CertificateManager;
import trial.http.ssl.SecureApiAttribute;

/**
 * リクルート用API属性クラス.
 *
 * @author nino
 */
public class ReqruitApiAttribute extends SecureApiAttribute {

    private static final String HOSTNAME = "webservice.recruit.co.jp";

    private static final String SEGMENT = "hotpepper/gourmet/v1";

    private static final String FILE_PATH_CER = "x.509.cer/recruit.base64.encoded.x.509.cer";

    /**
     * コンストラクタ.
     */
    public ReqruitApiAttribute() {
        super(HOSTNAME, SEGMENT, new CertificateManager(FILE_PATH_CER));
    }
}
