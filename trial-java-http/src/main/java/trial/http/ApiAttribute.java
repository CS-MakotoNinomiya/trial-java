package trial.http;

import lombok.Data;

/**
 * API属性クラス.
 *
 * @author nino
 */
@Data
public abstract class ApiAttribute {

    /** ホスト名. */
    private String hostname;
    /** セグメント. */
    private String segment;

    /**
     * コンストラクタ.
     *
     * @param hostname ホスト名
     * @param segment セグメント
     */
    public ApiAttribute(String hostname, String segment) {
        this.hostname = hostname;
        this.segment = segment;
    }
}
