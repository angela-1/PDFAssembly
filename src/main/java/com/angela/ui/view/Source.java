package com.angela.ui.view;

import com.angela.Utils;
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
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Source extends VBox {
    private final SimpleListProperty<String> source;

    private final ListView<Label> fileList;
    private final Label total;

    public Source() {
        setPadding(new Insets(8, 16, 8, 16));
        setSpacing(8.0);

        source = new SimpleListProperty<>(FXCollections.observableArrayList());

        Label srcLabel = new Label("文件列表");
        srcLabel.getStyleClass().add("h2");

        total = new Label("");

        fileList = new ListView<>();
        fileList.getStyleClass().add("file-list");
        fileList.setOnDragOver(dragEvent -> {
            dragEvent.acceptTransferModes(TransferMode.ANY);

        });
        fileList.setOnDragDropped(dragEvent -> {
            System.out.println("cao");
            if (dragEvent.getDragboard().hasFiles()) {
                List<File> files = dragEvent.getDragboard().getFiles();
                System.out.println("doppped" + files);
                setFiles(Utils.filter(files.stream().map(File::toString).collect(Collectors.toList())));
            }
        });

        FileChooser fileChooser = new FileChooser();

        HBox toolBar = new HBox();
        toolBar.setSpacing(8);
        Button addButton = new Button("添加");
        addButton.setOnMouseClicked(mouseEvent -> {
            List<File> selectedFile = fileChooser.showOpenMultipleDialog(null);
            if (selectedFile != null) {
                setFiles(selectedFile.stream().map(File::toString).collect(Collectors.toList()));
            }
            System.out.println("fi" + selectedFile);
        });

        Button clearButton = new Button("清空");
        clearButton.setOnMouseClicked(mouseEvent -> {
            source.clear();
            fileList.getItems().clear();
        });

        toolBar.getChildren().addAll(addButton, clearButton);

        getChildren().addAll(srcLabel, toolBar, fileList);
    }

    private void setFiles(List<String> files) {
        if (files == null || files.size() <= 0) {
            return;
        }
        ObservableList<String> oblist = FXCollections.observableList(files);
        this.source.clear();
        this.source.set(oblist);

        fileList.getItems().clear();
        int index = 1;
        for (var file : files) {
            fileList.getItems().add(showFile(index, file));
            System.out.println("add " + index);
            index++;
        }
        total.setText("共" + fileList.getItems().size() + "个文件");
    }

    private Label showFile(int index, String file) {
        return new Label(index + ". " + Paths.get(file).getFileName());
    }

    public ObservableList<String> getSource() {
        return source.get();
    }

    public SimpleListProperty<String> sourceProperty() {
        return source;
    }
}
