package trial.http.ssl;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/**
 * 証明書管理クラス.
 * 
 * @author nino
 */
public class CertificateManager {

    private CertificateLoader loader;

    private X509Certificate[] certificates;

    private X509TrustManager trustManager;

    /**
     * コンストラクタ.
     * 
     * @param path 証明書パス
     */
    public CertificateManager(String path) {
        this.loader = new CertificateLoader();
        this.certificates = loader.load(path);

        // 証明書マネージャを作成、および信頼できるサーバ証明書か検証
        this.trustManager = createTrustManager(certificates);
        checkServerTrusted(trustManager, certificates);
    }

    /**
     * 証明書情報を取得します.
     * 
     * @return 証明書情報
     */
    public X509Certificate[] getCertificates() {
        return this.certificates;
    }

    /**
     * TrustManagerを取得します.
     * 
     * @return TrustManager
     */
    public X509TrustManager getTrustManager() {
        return this.trustManager;
    }

    /**
     * TrustManagerを生成します.
     * 
     * @param certificates 証明書情報
     * @return TrustManager
     */
    private X509TrustManager createTrustManager(X509Certificate[] certificates) {
        TrustManager[] trustManagers = null;
        try {
            char[] password = "password".toCharArray();
            KeyStore keyStore = newEmptyKeyStore(password);

            int index = 0;
            for (X509Certificate certificate : certificates) {
                String certificateAlias = Integer.toString(index++);
                keyStore.setCertificateEntry(certificateAlias, certificate);
            }

            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyManagerFactory.init(keyStore, password);
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);

            trustManagers = trustManagerFactory.getTrustManagers();
            if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
                throw new IllegalStateException("Unexpected default trust managers : " + Arrays.toString(trustManagers));
            }
        } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e) {
            throw new RuntimeException("____ failed to create trust manager.", e);
        }
        return (X509TrustManager) trustManagers[0];
    }

    /**
     * 空のKeyStoreオブジェクトを生成します.
     * 
     * @param password パスワード
     * @return 空のKeyStoreオブジェクト
     */
    private KeyStore newEmptyKeyStore(char[] password) {
        KeyStore keyStore = getDefaultKeyStoreInstance();
        try {
            InputStream in = null;
            keyStore.load(in, password);
        } catch (NoSuchAlgorithmException | CertificateException | IOException e) {
            throw new RuntimeException("____ failed to load from KeyStore.", e);
        }
        return keyStore;
    }

    /**
     * デフォルトのKeyStoreオブジェクトを取得します.
     * 
     * @return デフォルトKeyStoreオブジェクト
     */
    private KeyStore getDefaultKeyStoreInstance() {
        try {
            return KeyStore.getInstance(KeyStore.getDefaultType());
        } catch (KeyStoreException e) {
            throw new RuntimeException("____ failed to get KeyStore default instance.", e);
        }
    }

    /**
     * 証明書をチェックします.
     * 
     * @param trustManager TrustManager
     * @param certificates 証明書
     */
    private void checkServerTrusted(X509TrustManager trustManager, X509Certificate[] certificates) {
        try {
            trustManager.checkServerTrusted(certificates, "SHA256withRSA");
        } catch (CertificateException e) {
            throw new RuntimeException("____ failed to check server trust.", e);
        }
    }
}
