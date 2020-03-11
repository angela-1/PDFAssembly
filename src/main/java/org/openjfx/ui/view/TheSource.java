package org.openjfx.ui.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TheSource extends VBox {
    private final SimpleListProperty<String> source;
    private final JFXListView<Label> filelist;
    private Label total;

    public TheSource(Stage stage) {
        this.source = new SimpleListProperty<String>(this,
                "source", FXCollections.observableArrayList());

        Label srcLabel = new Label("文件列表");
        srcLabel.getStyleClass().add("section");

        FileChooser fileChooser = new FileChooser();

        filelist = new JFXListView<Label>();

        filelist.setMinHeight(100);
        filelist.setOnDragOver(evt -> {
            System.out.println("over");
            if (evt.getDragboard().hasFiles()) {
                evt.acceptTransferModes(TransferMode.LINK);
            }
        });
        filelist.setOnDragDropped(dragEvent -> {
            List<File> files = dragEvent.getDragboard().getFiles();
            System.out.println("doppped" + files);
            this.setFiles(files);
        });

        HBox toolhbox = new HBox();
        toolhbox.setSpacing(8);
        toolhbox.setAlignment(Pos.CENTER_LEFT);
        JFXButton addButton = new JFXButton("添加");
        addButton.getStyleClass().add("tool-button");
        addButton.setOnMouseClicked(mouseEvent -> {
            List<File> selectedFile = fileChooser.showOpenMultipleDialog(stage);
            System.out.println("fi" + selectedFile);
            this.setFiles(selectedFile);
        });

        JFXButton clearButton = new JFXButton("清空");
        clearButton.getStyleClass().add("tool-button");
        clearButton.setOnMouseClicked(mouseEvent -> {
            this.source.clear();
            this.filelist.getItems().clear();
        });

        this.total = new Label("");

        toolhbox.getChildren().addAll(addButton, clearButton, total);

        this.setSpacing(8);
        this.getChildren().addAll(srcLabel, toolhbox, filelist);

    }

    private void setFiles(List<File> files) {
        if (files == null || files.size() <= 0) {
            return;
        }
        List<String> list = files.stream().map(File::toString).collect(Collectors.toList());
        ObservableList<String> oblist = FXCollections.observableList(list);
        this.source.clear();
        this.source.set(oblist);

        this.filelist.getItems().clear();
        int index = 1;
        for (File file : files) {
            this.filelist.getItems().add(this.showFile(index, file));
            System.out.println("add " + index);
            index++;
        }
        this.total.setText("共" + this.filelist.getItems().size() + "个文件");
    }

    private Label showFile(int index, File file) {
        return new Label(index + ". " + file.getName());
    }

    public ObservableList<String> getSource() {
        return source.get();
    }

    public SimpleListProperty<String> sourceProperty() {
        return source;
    }
}
