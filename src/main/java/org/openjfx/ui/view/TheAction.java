package org.openjfx.ui.view;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class TheAction extends HBox {
    private final JFXButton runButton;
    private final ProgressBar progressBar;

    public TheAction() {
        runButton = new JFXButton("运行");
        runButton.getStyleClass().addAll("button-raised", "action-button");

        // move outside below
//        runButton.setOnMouseClicked(mouseEvent -> {
//            this.run();
//            System.out.println("source " + this.source.get());
//            System.out.println("config " + this.configProp.get());
//        });

//        this.source.addListener((observable, oldValue, newValue) -> {
//            runButton.setDisable(newValue.size() <= 0);
//        });

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
