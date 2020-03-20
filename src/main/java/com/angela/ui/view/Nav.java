package com.angela.ui.view;

import com.angela.Utils;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class Nav extends VBox {
    private final SimpleStringProperty selected;

    public Nav() {
        getStyleClass().add("nav-bar");
//        setPadding(new Insets(16, 0, 0, 0));

        selected = new SimpleStringProperty("merge");

        Label mergeLabel = new Label(Utils.getTitle("merge"));
        mergeLabel.setId("merge");
        mergeLabel.setGraphic(Utils.getImageView("/image/merge.png", 20));

        Label convertLabel = new Label(Utils.getTitle("convert"));
        convertLabel.setId("convert");
        convertLabel.setGraphic(Utils.getImageView("/image/convert.png", 20));

        Label tocLabel = new Label(Utils.getTitle("toc"));
        tocLabel.setId("toc");
        tocLabel.setGraphic(Utils.getImageView("/image/toc.png", 20));

        Label pageNumberLabel = new Label(Utils.getTitle("pagenumber"));
        pageNumberLabel.setId("pagenumber");
        pageNumberLabel.setGraphic(Utils.getImageView("/image/pagenumber.png", 20));

        ListView<Label> navList = new ListView<>();

        navList.getItems().addAll(mergeLabel, convertLabel, tocLabel, pageNumberLabel);
        navList.getStyleClass().add("my-list-view");

        navList.getSelectionModel().select(0);
        navList.setOnMouseClicked(mouseEvent -> {
            String id = navList.getSelectionModel().getSelectedItem().getId();
            selected.set(id);
            System.out.println("nav to " + id);
        });

        VBox white = new VBox();
        white.getChildren().add(new Label("cnie"));
        getChildren().addAll(navList, white);
        VBox.setVgrow(white, Priority.ALWAYS);
    }

    public String getSelected() {
        return selected.get();
    }

    public SimpleStringProperty selectedProperty() {
        return selected;
    }
}
