package trial.executors;

import java.util.concurrent.Callable;

import lombok.Getter;

public class TrialTask implements Callable<Boolean>{

    @Getter
    private String id;

    @Getter
    private String name;

    public TrialTask(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public Boolean call() throws Exception {
        System.out.println(Thread.currentThread().getName());
        return true;
    }
}
