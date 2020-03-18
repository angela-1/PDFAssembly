package org.openjfx;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.openjfx.task.MyTask;
import org.openjfx.ui.view.*;

import java.util.Map;

public class MainApp extends Application {
    private ContextConfig config;

    @Override
    public void start(Stage primaryStage) {
        this.config = new ContextConfig();

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
        contentVbox.setPadding(new Insets(16, 16, 16, 16));
        contentVbox.setSpacing(8.0);

        // source
        TheSource theSource = new TheSource(primaryStage);
        contentVbox.getChildren().add(theSource);
        config.sourcePropProperty().bind(theSource.sourceProperty());

        // config
        TheConfig theConfig = new TheConfig();
        theConfig.titleProperty().bind(theNav.currentPageProperty());
        config.taskPropProperty().bind(theNav.currentPageProperty());


        contentVbox.getChildren().add(theConfig);

        // action
        TheAction theAction = new TheAction();
        TheStatus theStatus = new TheStatus();
        theAction.getRunButton().disableProperty().bind(theSource.sourceProperty().emptyProperty());
        theAction.getRunButton().setOnMouseClicked(mouseEvent -> {
            Map<String, Object> configMap = theConfig.getConfig();
            config.setConfig(configMap);
            MyTask task = Dispatcher.run(config);
            new Thread(task).start();
            theAction.getProgressBar().progressProperty().bind(task.progressProperty());
            theStatus.textPropProperty().bind(task.messageProperty());
        });


//        theAction.progressProperty().bind(task.progressProperty());

//        Button testButton = new Button("TEST");
//        testButton.setOnMouseClicked(mouseEvent -> {
//            System.out.println("click" + this.source.get());
//        });
//
//        contentVbox.getChildren().add(testButton);


        Separator separator = new Separator(Orientation.HORIZONTAL);

        contentVbox.getChildren().addAll(separator, theAction, theStatus);

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
