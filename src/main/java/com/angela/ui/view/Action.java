package com.angela.ui.view;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class Action extends VBox {
    private final Button runButton;
    private final ProgressBar progressBar;
    private final SimpleStringProperty textProp;


    public Action() {
        setPadding(new Insets(8, 16, 8, 16));

        textProp = new SimpleStringProperty("就绪");

        runButton = new Button("运行");
        runButton.getStyleClass().add("action-button");

        progressBar = new ProgressBar();
        progressBar.setPrefWidth(100);
        progressBar.visibleProperty().bind(
                progressBar.progressProperty().greaterThan(0).and(
                        progressBar.progressProperty().lessThan(1)
                ));

        HBox toolBar = new HBox();
        toolBar.setSpacing(16.0);
        toolBar.getChildren().addAll(runButton, progressBar);


        Label status = new Label();
        status.textProperty().bind(textProp);

        setSpacing(8.0);
        getChildren().addAll(toolBar, status);

    }

    public Button getRunButton() {
        return runButton;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public String getTextProp() {
        return textProp.get();
    }

    public SimpleStringProperty textPropProperty() {
        return textProp;
    }
}
