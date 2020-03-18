package org.openjfx;

import org.openjfx.task.MergeDoc;
import org.openjfx.task.MyTask;
import org.openjfx.task.pagenumber.PageNumber;

/**
 * Dispatcher
 */
public class Dispatcher {

    public static MyTask getTask(ContextConfig config) {
        String taskName = config.getContext().get("task").toString();
        MyTask task = null;
        switch (taskName) {
            case "merge":
                task = new MergeDoc();
                break;
//            case "convert":
//                configName = "ConvertConfig";
//                break;
//            case "toc":
//                configName = "TocConfig";
//                break;
            case "pagenumber":
                task = new PageNumber(config);
                break;
            default:
                task = new MergeDoc();
                break;
        }
        return task;
    }

    public static MyTask run(ContextConfig config) {
        System.out.println("run with config " + config.toString());
        return getTask(config);
    }

}