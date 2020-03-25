package com.angela.task.toc;

import com.angela.Context;
import com.angela.task.MyTask;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TocTask extends MyTask {

    private final String srcFile;
    private final TocFormat tocFormat;

    public TocTask(Context config) {
        System.out.println("toc constructor " + config.toString());

        List<String> source = new ArrayList<>();
        Object sourceObj = config.getContext().get("source");
        if (sourceObj instanceof List<?>) {
            for (Object o : (List<?>) sourceObj) {
                source.add((String) o);
            }
        }
        srcFile = source.get(0);
        Object configObj = config.getContext().get("config");
        Map<String, Object> configMap = new HashMap<>();
        if (configObj instanceof Map) {
            for (Object o : ((Map<?, ?>) configObj).keySet()) {
                String key = (String) o;
                configMap.put(key, ((Map<?, ?>) configObj).get(key));
            }
        }
        tocFormat = (TocFormat) configMap.get("outputFormat");
    }


    private String generateToc() {
        MyToc myToc = TocFactory.getTocFormat(srcFile, tocFormat);
        new Thread(myToc).start();
        myToc.progressProperty().addListener((observableValue, number, t1) -> {
            System.out.println("lis1 t1 " + t1.doubleValue());
            if (myToc.isRunning()) {
                updateProgress(MAX_PROGRESS * t1.doubleValue(), MAX_PROGRESS);
            } else {
                System.out.println("lis2");
                done();
                updateProgress(MAX_PROGRESS, MAX_PROGRESS);
            }

        });
        return myToc.getValue();
    }

    @Override
    public String runTask() {
        return generateToc();
    }
}
