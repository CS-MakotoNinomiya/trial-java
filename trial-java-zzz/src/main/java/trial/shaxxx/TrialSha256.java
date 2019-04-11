package trial.shaxxx;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.UUID;

public class TrialSha256 {

    public static void main(String[] args) {
        TrialSha256 me = new TrialSha256();
        me.execute(args);
    }

    private void execute(String[] args) {
        String toReturn = null;
        try {
            UUID uuid = UUID.randomUUID();
            String uuidstr = "m.ninomiya@central-soft.co.jp" + "-" + uuid.toString();
            System.out.println(uuidstr);

            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(uuidstr.getBytes("utf8"));
            toReturn = String.format("%040x", new BigInteger(1, digest.digest()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(toReturn);
    }
}
