package com.angela;


import com.angela.task.MergeDoc;
import com.angela.task.MyTask;
import com.angela.task.merge.MergeTask;
import com.angela.task.pagenumber.PageNumberTask;
import com.angela.task.toc.TocTask;

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
                task = new TocTask(config);
                break;
            case "pagenumber":
                task = new PageNumberTask(config);
                break;
            default:
                task = new MergeDoc();
                break;
        }
        return task;
    }

    public static MyTask run(Context config) {
        System.out.println("run with config " + config.toString());
        return getTask(config);
    }

}