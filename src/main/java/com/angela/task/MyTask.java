package com.angela.task;

import javafx.concurrent.Task;

public abstract class MyTask extends Task<String> {

    protected static final int MAX_PROGRESS = 100;

    @Override
    protected String call() throws Exception {
        updateMessage("处理中……");
        String result = runTask();
        System.out.println("result " + result);
        updateMessage("完成 结果位于：" + result);
        return result;
    }

    public abstract String runTask();
}
