package org.openjfx.ui.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.openjfx.ui.config.ConvertConfig;
import org.openjfx.ui.config.MergeConfig;
import org.openjfx.ui.config.PageNumberConfig;
import org.openjfx.ui.config.TocConfig;

public class TheConfig extends VBox {
    private final SimpleStringProperty title;

    public TheConfig() {
        this.setPadding(new Insets(8, 8, 8, 8));
        this.title = new SimpleStringProperty(this, "title", "merge");
        this.title.addListener((observable, oldValue, newValue) -> {
            System.out.println("listener " + newValue);
            this.setConfig(newValue);
        });
        this.setConfig("merge");
    }

    private void setConfig(String newValue) {
        System.out.println("set config " + newValue);
        Node node = this.getConfigArea(newValue);
        Label titleLabel = new Label(TheNav.getLabel(newValue));
        titleLabel.getStyleClass().add("section");
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
}
