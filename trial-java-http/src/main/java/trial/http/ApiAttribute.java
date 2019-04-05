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
    /** ポート番号. */
    private Integer port;

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

    /**
     * コンストラクタ.
     *
     * @param hostname ホスト名
     * @param segment セグメント
     * @param port ポート番号
     */
    public ApiAttribute(String hostname, String segment, Integer port) {
        this.hostname = hostname;
        this.segment = segment;
        this.port = port;
    }
}
