package trial.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * streamのお試し用.
 *
 * @author m.ninomiya
 */
public class TrialStream {

    public static void main(String[] args) {
        TrialStream me = new TrialStream();
        me.execute(args);
    }

    private void execute(String[] args) {

        String[] words = { "aaaaaa", "bbbbbb", "cccccc" };

        List<String> list = Arrays.asList(words);

        Optional<String> opt1 = list.stream().findFirst();
        opt1.ifPresent(System.out::println);

        Optional<String> opt2 = list.stream()
                .filter(str -> 0 <= str.indexOf("b"))
                .findFirst();
        opt2.ifPresent(System.out::println);

        hogehoge01();
        hogehoge02();
    }

    private void hogehoge01() {
        String[] data = { "1", "2", "3" };

        List<String> list = Arrays.asList(data);

        Optional<Integer> opt = list.stream()
                .filter(Objects::nonNull)
                .map(str -> {
                    return Integer.parseInt(str);
                })
                .filter(num -> num % 2 == 0)
                .findFirst();
        opt.ifPresent(System.out::println);
    }

    private void hogehoge02() {
        String[] data = { "1", "3", "5", "7" };
        List<String> list = Arrays.asList(data);

        Optional<Integer> opt = list.stream()
                .filter(Objects::nonNull)
                .map(str -> {
                    return Integer.parseInt(str);
                })
                .filter(num -> num % 2 == 0)
                .findFirst();
        Integer i = opt.orElse(new Integer(0));
        System.out.println(i);
    }
}
