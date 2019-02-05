package trial.http.ssl.attr;

import trial.http.ssl.CertificateManager;
import trial.http.ssl.SecureApiAttribute;

/**
 * リクルート用API属性クラス.
 *
 * @author nino
 */
public class QiitaApiAttribute extends SecureApiAttribute {

    private static final String HOSTNAME = "qiita.com";

    private static final String SEGMENT = "api/v2/authenticated_user/items";

    private static final String FILE_PATH_CER = "x.509.cer/qiita.base64.encoded.x.509.cer";

    /**
     * コンストラクタ.
     */
    public QiitaApiAttribute() {
        super(HOSTNAME, SEGMENT, new CertificateManager(FILE_PATH_CER));
    }
}
