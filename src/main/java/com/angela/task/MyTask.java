package com.angela.task;

import javafx.concurrent.Task;

public abstract class MyTask extends Task<String> {

    protected static final int MAX_PROGRESS = 100;

    @Override
    protected String call() throws Exception {
        updateMessage("处理中……");
        String result = runTask();
        updateMessage("完成");
        return result;
    }

    public abstract String runTask();
}
