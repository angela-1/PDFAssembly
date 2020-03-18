package org.openjfx;

import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashMap;
import java.util.Map;

/**
 * Dispatcher
 */
public class ContextConfig {

    private final SimpleStringProperty taskProp;
    private final SimpleListProperty<String> sourceProp;
    private Map<String, Object> config;

    public ContextConfig() {
        taskProp = new SimpleStringProperty("merge");
        sourceProp = new SimpleListProperty<String>(FXCollections.observableArrayList());
        config = null;
    }

    public Map<String, Object> getContext() {
        Map<String, Object> configMap = new HashMap<String, Object>();
        configMap.put("task", taskProp.get());
        configMap.put("source", sourceProp.get());
        configMap.put("config", config);
        return configMap;
    }
    @Override
    public String toString() {
        return getContext().toString();
    }

    public String getTaskProp() {
        return taskProp.get();
    }

    public SimpleStringProperty taskPropProperty() {
        return taskProp;
    }

    public ObservableList<String> getSourceProp() {
        return sourceProp.get();
    }

    public SimpleListProperty<String> sourcePropProperty() {
        return sourceProp;
    }

    public Map<String, Object> getConfig() {
        return config;
    }

    public void setConfig(Map<String, Object> config) {
        this.config = config;
    }
}