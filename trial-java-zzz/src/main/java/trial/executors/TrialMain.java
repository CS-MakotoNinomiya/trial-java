package trial.executors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TrialMain {

    public static void main(String[] args) {
        TrialMain me = new TrialMain();
        me.execute(args);
    }

    private void execute(String[] args) {

        TaskFactory taskFactory = new TaskFactory();
        ExecutorService pool = Executors.newFixedThreadPool(10, taskFactory);
        try {
            pool.invokeAll(taskFactory.getTaskList());
            pool.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
