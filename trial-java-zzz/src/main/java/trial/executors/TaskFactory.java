package trial.executors;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadFactory;

import lombok.Getter;

public class TaskFactory implements ThreadFactory {

    @Getter
    private List<Callable<Boolean>> taskList;

    private ArrayDeque<TrialTask> taskQue;

    /**
     * コンストラクタ.
     */
    public TaskFactory() {
        taskList = new ArrayList<>();
        taskQue = new ArrayDeque<>();
        TrialTask task1 = new TrialTask("1", "あああ");
        TrialTask task2 = new TrialTask("2", "いいい");
        TrialTask task3 = new TrialTask("3", "ううう");
        addTask(task1);
        addTask(task2);
        addTask(task3);
    }

    @Override
    public Thread newThread(Runnable r) {
        TrialTask task = taskQue.remove();
        return new Thread(null, r, "thread-" + task.getId() + "-" + task.getName());
//        return new Thread(null, r);
    }

    private void addTask(TrialTask task) {
        this.taskList.add(task);
        this.taskQue.add(task);
    }
}
