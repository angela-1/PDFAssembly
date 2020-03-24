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
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class TitleBar extends VBox {

    private final SimpleStringProperty selected;

    private final Button closeButton;

    public TitleBar() {
        selected = new SimpleStringProperty("merge");

        HBox hbox = new HBox();
        hbox.setAlignment(Pos.TOP_LEFT);

        ImageView logo = Utils.getImageView("/merge.png", 24);
        logo.setFitHeight(24);

        Label label = new Label(Utils.getTitle(selected.get()));
        label.setFont(Font.font(20));
        label.setPadding(new Insets(8));

        selected.addListener((observable, oldValue, newValue) -> {
            System.out.println("title bar listener " + newValue);
            label.setText(Utils.getTitle(newValue));
        });


        closeButton = new Button();
        closeButton.setGraphic(Utils.getImageView("/image/close.png", 16));
        closeButton.getStyleClass().add("close-button");

        HBox lefthbox = new HBox();
        lefthbox.setAlignment(Pos.BASELINE_LEFT);
        lefthbox.getChildren().addAll(logo, label);
        lefthbox.setPadding(new Insets(8, 0, 8, 16));
        hbox.getChildren().addAll(lefthbox, closeButton);
        HBox.setHgrow(lefthbox, Priority.ALWAYS);

        getChildren().add(hbox);
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
