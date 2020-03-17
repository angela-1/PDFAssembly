package org.openjfx;

import javafx.concurrent.Task;
import org.openjfx.task.MergeDoc;
import org.openjfx.task.MyTask;
import org.openjfx.task.PageNumber;

/**
 * Dispatcher
 */
public class Dispatcher {

    public static MyTask getTask(String taskName) {
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
                task = new PageNumber();
                break;
            default:
                task = new MergeDoc();
                break;
        }
        return task;
    }

    public static MyTask run(ContextConfig config) {
        System.out.println("run with config " + config.toString());
        return getTask(config.get().get("task").toString());
//        return new Task<>() {
//            @Override
//            public Void call() throws InterruptedException {
//                MyTask task = getTask(config.getConfig().get("task").toString());
//                task.runTask();
//                new Thread(task).start();
//
////                final int max = 50;
////                for (int i = 1; i <= max; i++) {
////                    Thread.sleep(20);
////                    if (isCancelled()) {
////                        break;
////                    }
////                    System.out.println("ms" + i);
////                    updateProgress(i, max);
////                }
//                return null;
//            }
//        };
    }


}