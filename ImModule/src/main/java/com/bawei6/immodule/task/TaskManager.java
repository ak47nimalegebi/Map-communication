package com.bawei6.immodule.task;

import java.util.concurrent.Executors;

public class TaskManager {

    private static TaskManager instance=new TaskManager();
    private TaskManager() {}
    public static TaskManager getInstance(){
        return instance;
    }

    public void doTask(Runnable runnable){
        Executors.newCachedThreadPool().execute(runnable);
    }
}
