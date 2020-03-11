package org.openjfx.ui.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXToolbar;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class TheHeader extends VBox {

    public TheHeader() {
        JFXToolbar headerBar = new JFXToolbar();

        String logoUrl = this.getClass().getResource("/img/logo.png").toExternalForm();
        final ImageView imageView = new ImageView(logoUrl);
        imageView.setFitWidth(36);
        imageView.setPreserveRatio(true);

        Label logoLabel = new Label("PDFMerge");
        headerBar.setLeftItems(imageView, logoLabel);


        JFXButton aboutButton = new JFXButton("关于");
        aboutButton.setGraphic(new ImageView());
        JFXButton helpButton = new JFXButton();
        String helpUrl = this.getClass().getResource("/img/help.png").toExternalForm();
        ImageView helpIcon = new ImageView(helpUrl);
        helpIcon.setFitWidth(24);
        helpIcon.setPreserveRatio(true);
        helpButton.setGraphic(helpIcon);
        headerBar.setRightItems(aboutButton, helpButton);


        this.getChildren().add(headerBar);
    }
}
