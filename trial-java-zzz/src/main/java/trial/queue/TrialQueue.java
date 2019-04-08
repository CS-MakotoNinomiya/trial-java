package trial.queue;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class TrialQueue {

    public static void main(String[] args) {
        TrialQueue me = new TrialQueue();

        System.out.println("__ start execute.");
        me.execute(args);
        System.out.println("__ end execute.");
    }

    private void execute(String[] args) {
        Queue<Item> queue = new LinkedBlockingQueue<Item>();
        Item item1 = new Item("1", "a");
        Item item2 = new Item("2", "b");
        queue.add(item1);
        queue.add(item2);

        while (!queue.isEmpty()) {
            Item item = queue.remove();
            System.out.println(item.toString());
        }
    }


    private class Item {
        private String id;
        private String name;

        private Item(String id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return id + " " + name;
        }
    }
}
