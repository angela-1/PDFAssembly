package com.angela.task.toc;

import com.angela.Context;
import com.angela.task.MyTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Toc {

    public static MyTask getTask(Context config){
        System.out.println("toc get task " + config.toString());

        List<String> source = new ArrayList<>();
        Object sourceObj = config.getContext().get("source");
        if (sourceObj instanceof List<?>) {
            for (Object o : (List<?>) sourceObj) {
                source.add((String) o);
            }
        }
        String srcFile = source.get(0);
        Object configObj = config.getContext().get("config");
        Map<String, Object> configMap = new HashMap<>();
        if (configObj instanceof Map) {
            for (Object o : ((Map<?, ?>) configObj).keySet()) {
                String key = (String) o;
                configMap.put(key, ((Map<?, ?>) configObj).get(key));
            }
        }
        TocFormat tocFormat = (TocFormat) configMap.get("outputFormat");
        return TocTaskFactory.getFormatTask(srcFile, tocFormat);
    }
}
