package trial.http.ssl;

import java.io.InputStream;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 証明書読み込みクラス.
 * 
 * @author nino
 */
public class CertificateLoader {

    /**
     * 証明書を読み込みます.
     * 
     * @param path 証明書パス
     * @return X509Certificate配列
     */
    public X509Certificate[] load(String path) {
        List<X509Certificate> certificateList = new ArrayList<>();
        try {
            Collection<? extends Certificate> certificates = getCertificates(path);
            for (Certificate certificate : certificates) {
                X509Certificate x509certificate = (X509Certificate) certificate;
                x509certificate.checkValidity(); // 証明書が現在有効であるかどうかを判定
                certificateList.add(x509certificate);
            }
        } catch (CertificateExpiredException | CertificateNotYetValidException e) {
            throw new RuntimeException("____ failed to check valid cer file.", e);
        }
        return certificateList.toArray(new X509Certificate[certificateList.size()]);
    }

    /**
    * 証明書情報を取得します.
    * 
    * @param path 証明書パス
    * @return 証明書情報
    */
    private Collection<? extends Certificate> getCertificates(String path) {
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            InputStream in = this.getClass().getClassLoader().getResourceAsStream(path);
            Collection<? extends Certificate> certificates = certificateFactory.generateCertificates(in);
            if (certificates.isEmpty()) {
                throw new IllegalArgumentException("expected non-empty set of trusted certificates");
            }
            return certificates;
        } catch (CertificateException e) {
            throw new RuntimeException("____ failed to get certificates.");
        }
    }
}
