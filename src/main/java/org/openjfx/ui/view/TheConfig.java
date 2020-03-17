package org.openjfx.ui.view;

import javafx.beans.property.SimpleMapProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.openjfx.ContextConfig;
import org.openjfx.ui.config.*;

import java.util.HashMap;
import java.util.Map;

public class TheConfig extends VBox {
    private final SimpleStringProperty title;
    private Node node = null;

    public TheConfig() {
        setConfig("merge");
        setPadding(new Insets(8, 8, 8, 8));
        title = new SimpleStringProperty(this, "title", "merge");
        title.addListener((observable, oldValue, newValue) -> {
            System.out.println("listener " + newValue);
            setConfig(newValue);
        });
    }

    private void setConfig(String newValue) {
        System.out.println("set config " + newValue);
        node = getConfigArea(newValue);
        Label titleLabel = new Label(TheNav.getLabel(newValue));
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


    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public Map<String, Object> getConfig() {
        return ((MyConfig) node).getConfig();
    }

}
