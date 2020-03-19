package com.angela;

import com.angela.ui.view.MainPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        MainPane mainPane = new MainPane();

        var scene = new Scene(mainPane, 640, 480);
        scene.getStylesheets()
                .add(getClass().getResource("/css/style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}