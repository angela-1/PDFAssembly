package com.angela.ui.view;

import com.angela.Utils;
import com.angela.ui.config.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.Map;

public class Content extends VBox {

    private final SimpleStringProperty selected;
    private Node node = null;

    public Content() {
        setPadding(new Insets(8));

        setConfig("merge");
        selected = new SimpleStringProperty("merge");
        selected.addListener((observable, oldValue, newValue) -> {
            System.out.println("content listener " + newValue);
            setConfig(newValue);
        });
    }

    private void setConfig(String newValue) {
        System.out.println("set config " + newValue);
        node = getConfigArea(newValue);
        Label titleLabel = new Label(Utils.getTitle(newValue));
        titleLabel.getStyleClass().add("h2");
        getChildren().setAll(titleLabel, node);
    }

    private Node getConfigArea(String pageId) {
        Node node;
        switch (pageId) {
            case "merge":
                node = new MergeConfig();
                break;
            case "convert":
                node = new ConvertConfig();
                break;
            case "toc":
                node = new TocConfig();
                break;
            case "pagenumber":
                node = new PageNumberConfig();
                break;
            default:
                node = new MergeConfig();
                break;
        }
        return node;
    }

    public Map<String, Object> getConfig() {
        return ((MyConfig) node).getConfig();
    }

    public String getSelected() {
        return selected.get();
    }

    public SimpleStringProperty selectedProperty() {
        return selected;
    }
}
