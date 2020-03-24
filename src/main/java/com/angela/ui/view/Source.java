package com.angela.ui.view;

import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class Source extends VBox {
    private final SimpleListProperty<String> source;

    private final ListView<Label> fileList;
    private final Label total;

    public Source() {
        setPadding(new Insets(8,16,8,16));
        setSpacing(8.0);

        source = new SimpleListProperty<String>(FXCollections.observableArrayList());

        Label srcLabel = new Label("文件列表");
        srcLabel.getStyleClass().add("h2");

        total = new Label("");

        fileList = new ListView<>();
        fileList.setOnDragOver(dragEvent -> {
            System.out.println("over");
            if (dragEvent.getDragboard().hasFiles()) {
                dragEvent.acceptTransferModes(TransferMode.LINK);
            }
        });
        fileList.setOnDragDropped(dragEvent -> {
            if (dragEvent.getDragboard().hasFiles()) {
                dragEvent.acceptTransferModes(TransferMode.LINK);
                List<File> files = dragEvent.getDragboard().getFiles();
                System.out.println("doppped" + files);
                this.setFiles(files);
            }
        });

        FileChooser fileChooser = new FileChooser();

        HBox toolBar = new HBox();
        toolBar.setSpacing(8);
        Button addButton = new Button("添加");
        addButton.setOnMouseClicked(mouseEvent -> {
            List<File> selectedFile = fileChooser.showOpenMultipleDialog(null);
            System.out.println("fi" + selectedFile);
            this.setFiles(selectedFile);
        });

        Button clearButton = new Button("清空");
        clearButton.setOnMouseClicked(mouseEvent -> {
            source.clear();
            fileList.getItems().clear();
        });

        toolBar.getChildren().addAll(addButton, clearButton);

        getChildren().addAll(srcLabel, toolBar, fileList);
    }

    private void setFiles(List<File> files) {
        if (files == null || files.size() <= 0) {
            return;
        }
        List<String> list = files.stream().map(File::toString).collect(Collectors.toList());
        ObservableList<String> oblist = FXCollections.observableList(list);
        this.source.clear();
        this.source.set(oblist);

        fileList.getItems().clear();
        int index = 1;
        for (File file : files) {
            fileList.getItems().add(this.showFile(index, file));
            System.out.println("add " + index);
            index++;
        }
        total.setText("共" + fileList.getItems().size() + "个文件");
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
