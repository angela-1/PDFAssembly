package com.angela.ui.view;

import com.angela.Utils;
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

    private final Button closeButton;

    public TitleBar() {
        getStylesheets()
                .add(getClass().getResource("/css/title-bar.css").toExternalForm());

        setPadding(new Insets(0));
        getStyleClass().add("title-bar");

        GridPane gridPane = new GridPane();
        gridPane.setPrefHeight(32);
        gridPane.setAlignment(Pos.CENTER_LEFT);

        ImageView logo = Utils.getImageView("/merge.png", 28);
        logo.setFitHeight(28);
        GridPane.setMargin(logo, new Insets(2, 8, 2, 8));

        Label label = new Label("PDFAssembly");
//        label.setStyle("-fx-font-size: 14px");
        label.setFont(Font.font(14));

        closeButton = new Button("X");
        closeButton.getStyleClass().add("title-button");
        GridPane.setMargin(closeButton, new Insets(0));
        gridPane.setAlignment(Pos.CENTER_LEFT);

        gridPane.addColumn(0, logo);
        gridPane.addColumn(1, label);
        gridPane.addColumn(2, closeButton);
        GridPane.setHgrow(label, Priority.ALWAYS);

        getChildren().add(gridPane);
    }

    public Button getCloseButton() {
        return closeButton;
    }
}
