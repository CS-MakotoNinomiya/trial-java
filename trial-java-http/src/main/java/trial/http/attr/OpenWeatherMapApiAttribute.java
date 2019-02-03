package trial.http.attr;

import trial.http.ApiAttribute;

/**
 * OpenWeatherMap API用 属性クラス.
 *
 * @author nino
 */
public class OpenWeatherMapApiAttribute extends ApiAttribute {

    private static final String HOSTNAME = "api.openweathermap.org";

    private static final String SEGMENT = "data/2.5/weather";

    /**
     * コンストラクタ.
     */
    public OpenWeatherMapApiAttribute() {
        super(HOSTNAME, SEGMENT);
    }
}
