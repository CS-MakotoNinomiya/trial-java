package trial.arraylist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrialArrayList {

    public static void main(String[] args) {
        TrialArrayList trial = new TrialArrayList();

        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(4, 5, 6);
        //List<Integer[]> list = trial.convertTwoListToArray1(list1, list2);
        List<Integer[]> list = trial.convertTwoListToArray2(list1, list2);
        list.forEach(System.out::println);
    }

    /**
     * ArrayListにnullを追加してみる.
     */
    private void trialAddNull() {
        List<String> list = new ArrayList<>();
        list.add(null);
        System.out.println(list.size());
    }

    /**
     * サイズが同じ２つのListを１つの配列Listにする.
     *
     * @param list1 リスト１
     * @param list2 リスト２
     * @return 配列リスト
     */
    private List<Integer[]> convertTwoListToArray1(List<Integer> list1, List<Integer> list2) {
        List<Integer[]> list = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++) {
            list.add(new Integer[] { list1.get(i), list2.get(i) });
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    private <T> List<T[]> convertTwoListToArray2(List<T> list1, List<T> list2) {
        List<T[]> list = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++) {
            Object[] tmpArray = new Object[] { list1.get(i), list2.get(i) };
            list.add((T[]) tmpArray);
        }
        return list;
    }
}
