package com.angela;

import com.angela.ui.view.Frame;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * JavaFX App
 */
public class App extends Application {
    private static App mInstance;

    public static App getInstance() {
        return mInstance;
    }

    @Override
    public void start(Stage stage) {
        mInstance = this;
        Frame frame = new Frame(stage);
        var scene = new Scene(frame, 640, 480);

        scene.getStylesheets()
                .add(getClass().getResource("/css/style.css").toExternalForm());

        stage.initStyle(StageStyle.UNDECORATED);
        String logoUrl = this.getClass().getResource("/merge.png").toExternalForm();
        stage.getIcons().add(new Image(logoUrl));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}