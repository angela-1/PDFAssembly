package com.angela.task;

import javafx.concurrent.Task;

public abstract class MyTask extends Task<String> {

    protected static final int MAX_PROGRESS = 100;

    @Override
    protected String call() throws Exception {
        return this.runTask();
    }

    public abstract String runTask();
}
