package com.angela;


import com.angela.task.MyTask;
import com.angela.task.merge.MergeTask;
import com.angela.task.pagenumber.PageNumberTask;
import com.angela.task.toc.Toc;

/**
 * Dispatcher
 */
public class Dispatcher {

    public static MyTask getTask(Context config) {
        String taskName = config.getContext().get("task").toString();
        MyTask task = null;
        switch (taskName) {
            case "merge":
                task = new MergeTask(config);
                break;
            case "toc":
                task = Toc.getTask(config);
                break;
            case "pagenumber":
                task = new PageNumberTask(config);
                break;
            default:
                task = new MergeTask(config);
                break;
        }
        return task;
    }

    public static MyTask run(Context config) {
        System.out.println("run with config " + config.toString());
        return getTask(config);
    }

}