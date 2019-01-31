package trial.http.request;

import trial.http.ssl.CertificateManager;

/**
 * リクエスト管理クラス.
 * 
 * @author nino
 */
public abstract class RequestManager {

    private CertificateManager certMgr;

    /**
     * コンストラクタ.
     */
    public RequestManager() {
        this.certMgr = new CertificateManager(getCertificatePath());
    }

    public CertificateManager getCertificateManager() {
        return this.certMgr;
    }

    protected abstract String getCertificatePath();

    public abstract String getHostname();

    public abstract String getSegment();
}
