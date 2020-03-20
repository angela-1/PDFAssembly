package com.angela.ui.view;

import com.angela.Context;
import com.angela.Dispatcher;
import com.angela.SystemInfo;
import com.angela.task.MyTask;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Separator;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.util.Map;

public class MainPane extends BorderPane {

    private final Context context;

    public MainPane() {
        setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("mout drag");
            }
        });
        context = new Context();

        // main pane
        setPadding(new Insets(0, 0, 0, 0));

        Nav nav = new Nav();
        setLeft(nav);
        context.taskPropProperty().bind(nav.selectedProperty());

        VBox contentVbox = new VBox();
        contentVbox.setPadding(new Insets(8));
        contentVbox.setSpacing(8.0);

        Source source = new Source();
        context.sourcePropProperty().bind(source.sourceProperty());

        Content content = new Content();
        content.selectedProperty().bind(nav.selectedProperty());

        Action action = new Action();
        action.getRunButton().disableProperty().bind(source.sourceProperty().emptyProperty());
        action.getRunButton().setOnMouseClicked(mouseEvent -> {
            Map<String, Object> configMap = content.getConfig();
            System.out.println("click action");
            context.setConfig(configMap);
            MyTask task = Dispatcher.run(context);
            new Thread(task).start();
            action.getProgressBar().progressProperty().bind(task.progressProperty());
            action.textPropProperty().bind(task.messageProperty());
        });

        Separator separator = new Separator();

        contentVbox.getChildren().addAll(source, content, separator, action);

        setCenter(contentVbox);


        Header header = new Header();
        TitleBar titleBar = new TitleBar();
//        setTop(header);
        setTop(titleBar);

        var javaVersion = SystemInfo.javaVersion();
        var javafxVersion = SystemInfo.javafxVersion();

        var label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        setBottom(label);


    }
}
