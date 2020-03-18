package org.openjfx.ui.view;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class TheAction extends HBox {
    private final JFXButton runButton;
    private final ProgressBar progressBar;

    public TheAction() {
        runButton = new JFXButton("运行");
        runButton.getStyleClass().addAll("button-raised", "action-button");

        SimpleDoubleProperty progress = new SimpleDoubleProperty(this, "progress", 0);
        progressBar = new ProgressBar();
        progressBar.progressProperty().bind(progress);

        progress.addListener((observable, oldValue, newValue) -> {
            System.out.println("orpg " + oldValue + " " + newValue);
        });

        HBox.setHgrow(progressBar, Priority.ALWAYS);
        this.setSpacing(16.0);
        this.setAlignment(Pos.CENTER_LEFT);
        this.getChildren().addAll(runButton, progressBar);

    }



    public JFXButton getRunButton() {
        return runButton;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }
}
