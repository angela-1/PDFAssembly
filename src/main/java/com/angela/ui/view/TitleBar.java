package com.angela.ui.view;

import com.angela.Utils;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class TitleBar extends VBox {

    private final SimpleStringProperty selected;

    private final Button closeButton;

    public TitleBar() {
        selected = new SimpleStringProperty("merge");

        getStyleClass().add("title-bar");


        GridPane gridPane = new GridPane();
//        gridPane.setPrefHeight(32);
//        gridPane.setAlignment(Pos.BASELINE_CENTER);

        ImageView logo = Utils.getImageView("/merge.png", 28);
        logo.setFitHeight(28);
        GridPane.setMargin(logo, new Insets(2, 8, 2, 16));

        Label label = new Label(Utils.getTitle(selected.get()));
        label.setFont(Font.font(20));
        label.setPadding(new Insets(8));

        selected.addListener((observable, oldValue, newValue) -> {
            System.out.println("title bar listener " + newValue);
            label.setText(Utils.getTitle(newValue));
        });


        closeButton = new Button();
        closeButton.setGraphic(Utils.getImageView("/image/close.png", 24));
        closeButton.getStyleClass().add("close-button");
        GridPane.setMargin(closeButton, new Insets(0));
        gridPane.setAlignment(Pos.CENTER_LEFT);

        gridPane.addColumn(0, logo);
        gridPane.addColumn(1, label);
        gridPane.addColumn(2, closeButton);
        GridPane.setHgrow(label, Priority.ALWAYS);

        getChildren().add(gridPane);
    }

    public String getSelected() {
        return selected.get();
    }

    public SimpleStringProperty selectedProperty() {
        return selected;
    }

    public Button getCloseButton() {
        return closeButton;
    }
}
