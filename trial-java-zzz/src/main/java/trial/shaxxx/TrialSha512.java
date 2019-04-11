package trial.shaxxx;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.AlgorithmConstraints;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.UUID;

import javax.xml.crypto.AlgorithmMethod;

public class TrialSha512 {

    public static void main(String[] args) {
        TrialSha512 me = new TrialSha512();
        me.execute(args);
    }

    private void execute(String[] args) {
        String toReturn = null;
        try {
            UUID uuid = UUID.randomUUID();
            String uuidstr = uuid.toString();
            System.out.println(uuidstr);

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.reset();
            digest.update(uuidstr.getBytes("utf8"));
            toReturn = String.format("%040x", new BigInteger(1, digest.digest()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(toReturn);

        System.out.println(encode(toReturn));
    }

    /**
     * エンコードします.
     *
     * @param source 対象文字列
     * @return エンコード後の文字列
     */
    private String encode(String source) {
        // エンコード処理
        byte[] bytes = source.getBytes(StandardCharsets.UTF_8);
        return Base64.getEncoder().encodeToString(bytes);
    }
}
