package trial.http.ssl;

import lombok.Getter;
import trial.http.ApiAttribute;

/**
 * SSL用API属性クラス.
 *
 * @author nino
 */
public abstract class SecureApiAttribute extends ApiAttribute {

    @Getter
    private CertificateManager certMgr;

    /**
     * コンストラクタ.
     *
     * @param hostname ホスト名
     * @param segment セグメント
     * @param certMgr 証明書管理オブジェクト
     */
    public SecureApiAttribute(String hostname, String segment, CertificateManager certMgr) {
        super(hostname, segment);
        this.certMgr = certMgr;
    }

    /**
     * コンストラクタ.
     *
     * @param hostname ホスト名
     * @param segment セグメント
     * @param port ポート番号
     * @param certMgr 証明書管理オブジェクト
     */
    public SecureApiAttribute(String hostname, String segment, Integer port, CertificateManager certMgr) {
        super(hostname, segment, port);
        this.certMgr = certMgr;
    }
}
