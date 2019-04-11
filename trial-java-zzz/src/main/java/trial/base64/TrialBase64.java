package trial.base64;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Base64のお試しクラス.
 *
 * @author nino
 */
public class TrialBase64 {

    /**
     * メイン.
     *
     * @param args プログラム引数
     */
    public static void main(String[] args) {
        try {
            (new TrialBase64()).execute(args);
        } catch (Throwable t) {
            System.err.println(t.getMessage());
            t.printStackTrace();
        }
    }

    /**
     * 主処理.
     *
     * @param args プログラム引数
     */
    private void execute(String[] args) {
        // エンコードしたい文字列
        String source = "m.ninomiya@central-soft.co.jp:::xxxxx";

        // エンコード
        String result = encode(source);
        System.out.format("エンコード結果 = %1$s" + System.lineSeparator(), result);

        // デコード
        result = decode(result);
        System.out.format("デコード結果   = %1$s" + System.lineSeparator(), result);
    }

    /**
     * エンコードします.
     *
     * @param source 対象文字列
     * @return エンコード後の文字列
     */
    public String encode(String source) {
        // エンコード処理
        byte[] bytes = source.getBytes(StandardCharsets.UTF_8);
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * でコードします.
     *
     * @param source 対象文字列
     * @return でコード後の文字列
     */
    public String decode(String source) {
        // デコード処理
        byte[] bytes = Base64.getDecoder().decode(source);
        return new String(bytes, StandardCharsets.UTF_8);
    }
}
