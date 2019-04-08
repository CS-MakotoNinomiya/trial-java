package trial.function;

import java.util.function.Function;

/**
 * functionパッケージのお試し用.
 *
 * @author m.ninomiya
 */
public class TrialFunction {

    public static void main(String[] args) {
        TrialFunction me = new TrialFunction();
        me.execute(args);
    }

    private void execute(String[] args) {
        Function<String, String> trialFunc1 = new Function<String, String>() {
            @Override
            public String apply(String str) {
                return "trialFunc1 " + str;
            }
        };
        callFunc(trialFunc1);

        Function<String, String> trialFunc2 = str -> {
            return "trialFunc2 " + str;
        };
        callFunc(trialFunc2);
    }

    private void callFunc(Function<String, String> func) {
        System.out.println(func.apply("test"));
    }
}
