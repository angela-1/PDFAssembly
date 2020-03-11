package org.openjfx.ui.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXProgressBar;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import org.openjfx.Dispatcher;

import java.io.File;
import java.util.List;

public class TheAction extends HBox {
    private final SimpleDoubleProperty progress;

    private final SimpleListProperty<String> source;
    private final SimpleStringProperty title;

    public TheAction() {

        this.source = new SimpleListProperty<String>(this,
                "source", FXCollections.observableArrayList());

        this.title = new SimpleStringProperty(this, "title", "merge");


        JFXButton runButton = new JFXButton("运行");
        runButton.setDisable(this.source.isEmpty());
        runButton.getStyleClass().addAll("button-raised", "action-button");
        runButton.setOnMouseClicked(mouseEvent -> {
            this.run();
            System.out.println("chuli " + this.source.get());
        });

        this.source.addListener((observable, oldValue, newValue) -> {
            runButton.setDisable(newValue.size() <= 0);
        });

        this.progress = new SimpleDoubleProperty(this, "progress", 0);
        ProgressBar progressBar = new ProgressBar();
        progressBar.progressProperty().bind(this.progress);

        this.progress.addListener((observable, oldValue, newValue) -> {
            System.out.println("orpg " + oldValue + " " + newValue);
        });
        HBox.setHgrow(progressBar, Priority.ALWAYS);
        this.setSpacing(16.0);
        this.setAlignment(Pos.CENTER_LEFT);
        this.getChildren().addAll(runButton, progressBar);
    }


    public void run() {
        System.out.println("config" + this.title.get() + this.source.get());
        Task<Void> task = new Task<Void>() {
            @Override
            public Void call() throws InterruptedException {
                final int max = 100;

                for (int i = 1; i <= max; i++) {
                    Thread.sleep(50);
                    if (isCancelled()) {
                        break;
                    }
                    System.out.println("ms" + i);
                    updateProgress(i, max);
                }
                return null;
            }
        };

        this.progress.bind(task.progressProperty());
        new Thread(task).start();
    }


    public SimpleDoubleProperty progressProperty() {
        return progress;
    }

    public ObservableList<String> getSource() {
        return source.get();
    }

    public SimpleListProperty<String> sourceProperty() {
        return source;
    }

    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }
}
