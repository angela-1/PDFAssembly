package com.angela.ui.view;

import com.angela.Utils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class TitleBar extends VBox {

    public TitleBar() {
        GridPane gridPane = new GridPane();
        gridPane.setPrefHeight(32);
        gridPane.setAlignment(Pos.CENTER_LEFT);

        ImageView logo = Utils.getImageView("/merge.png", 36);
        logo.setFitHeight(24);

        Button closeButton = new Button("X");

        gridPane.addColumn(0, logo);
        GridPane.setHgrow(logo, Priority.ALWAYS);
        gridPane.addColumn(1, closeButton);

        getChildren().add(gridPane);
    }
}
