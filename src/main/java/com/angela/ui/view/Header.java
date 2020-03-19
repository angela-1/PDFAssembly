package com.angela.ui.view;

import com.angela.Utils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class Header extends HBox {

    public Header() {
        setAlignment(Pos.CENTER);
        setPadding(new Insets(8));

        ImageView logo = Utils.getImageView("/merge.png", 36);
        Label logoLabel = new Label("PDFAssembly");

        getChildren().addAll(logo, logoLabel);

        HBox rightItems = new HBox();
        rightItems.setAlignment(Pos.CENTER_RIGHT);
        Button helpButton = new Button();
        ImageView helpIcon = Utils.getImageView("/image/help.png", 20);
        helpButton.setGraphic(helpIcon);
        rightItems.getChildren().add(helpButton);

        getChildren().add(rightItems);

        HBox.setHgrow(rightItems, Priority.ALWAYS);
    }
}
