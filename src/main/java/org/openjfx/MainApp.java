package org.openjfx;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTabPane;
import javafx.application.Application;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.openjfx.ui.view.*;

import java.io.File;
import java.util.List;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // main pane
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(0, 0, 0, 0));

        // navbar
        TheNav theNav = new TheNav();
        borderPane.setLeft(theNav);

        // appbar
        TheHeader theHeader = new TheHeader();
        borderPane.setTop(theHeader);

        // content
        VBox contentVbox = new VBox();
        contentVbox.setPadding(new Insets(8, 8, 8, 8));
        contentVbox.setSpacing(8.0);

        // source
        TheSource theSource = new TheSource(primaryStage);

        contentVbox.getChildren().add(theSource);

        // config
        TheConfig theConfig = new TheConfig();
        theConfig.titleProperty().bind(theNav.currentPageProperty());

        contentVbox.getChildren().add(theConfig);

        // action
        TheAction theAction = new TheAction();
        theAction.sourceProperty().bind(theSource.sourceProperty());
        theAction.titleProperty().bind(theConfig.titleProperty());

//        Task task = new Task<Void>() {
//            @Override
//            public Void call() throws InterruptedException {
//                final int max = 100;
//
//                for (int i = 1; i <= max; i++) {
//                    Thread.sleep(50);
//                    if (isCancelled()) {
//                        break;
//                    }
//                    System.out.println("ms" + i);
//                    updateProgress(i, max);
//                }
//                return null;
//            }
//        };

//        theAction.progressProperty().bind(task.progressProperty());

//        Button testButton = new Button("TEST");
//        testButton.setOnMouseClicked(mouseEvent -> {
//            System.out.println("click" + this.source.get());
//        });
//
//        contentVbox.getChildren().add(testButton);


        Separator separator = new Separator(Orientation.HORIZONTAL);

        contentVbox.getChildren().addAll(separator, theAction);

        borderPane.setCenter(contentVbox);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);

        primaryStage.setWidth(600);
        primaryStage.setHeight(500);

        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(500);
        primaryStage.setTitle("测试");
        String logoUrl = this.getClass().getResource("/img/logo.png").toExternalForm();
        primaryStage.getIcons().add(new Image(logoUrl));

        scene.getStylesheets().add(MainApp.class.getResource("/css/jfoenix-components.css").toExternalForm());
        scene.getStylesheets()
                .add(MainApp.class.getResource("/css/my.css").toExternalForm());

        primaryStage.show();


//        stage.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
