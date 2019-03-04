package trial.arraylist;

import java.util.ArrayList;
import java.util.List;

public class TrialArrayList2 {

    public static void main(String[] args) {
        TrialArrayList2 me = new TrialArrayList2();
        me.execute(args);
    }

    private void execute(String[] args) {
         ArrayList<String> list1 = new ArrayList<>();

        for (int i = 0; i < 10000000; i++) {
            list1.add(String.valueOf(i));
        }

        toArray1(list1);

        toArray2(list1);
    }

    private void toArray1(List<String> list) {
        long s = System.currentTimeMillis();
        String[] strArray = list.toArray(new String[0]);
        System.out.println(System.currentTimeMillis() - s);
    }

    private void toArray2(List<String> list) {
        long s = System.currentTimeMillis();
        String[] strArray = list.toArray(new String[list.size()]);
        System.out.println(System.currentTimeMillis() - s);
    }
}
