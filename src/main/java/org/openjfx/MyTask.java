package org.openjfx;

import javafx.concurrent.Task;

public abstract class MyTask extends Task<String> {
    @Override
    protected String call() throws Exception {
        return this.runTask();
    }

    public abstract String runTask();
}
