package org.openjfx;

import javafx.concurrent.Task;

public abstract class MyTask {
    private Task<String> task;

    public MyTask() {
        this.task = null;
    }
    
    public abstract String runTask();   

    public void run() {
        this.task = new Task<String>() {
            @Override
            public String call() throws InterruptedException {
                return rumTask();
            }
        };

        new Thread(task).start();
    }

    

    public Task<String> getTask() {
        return this.task;
    }
}
