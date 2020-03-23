package com.angela.ui.config;

import com.angela.task.MyTask;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.Map;

public class ConvertConfig extends VBox implements MyConfig {
    private String outputFormat;

    public ConvertConfig() {
        outputFormat = "pdf";

        final ToggleGroup group = new ToggleGroup();
        RadioButton pdfRadio = new RadioButton("PDF(*.pdf)");
        pdfRadio.setUserData("pdf");
        RadioButton docxRadio = new RadioButton("Word(*.docx)");
        docxRadio.setUserData("docx");
        RadioButton txtRadio = new RadioButton("文本文件(*.txt)");
        txtRadio.setUserData("txt");

        pdfRadio.setToggleGroup(group);
        pdfRadio.setSelected(true);
        docxRadio.setToggleGroup(group);
        txtRadio.setToggleGroup(group);

        group.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("group listener " + newValue.getUserData().toString());
            outputFormat = (String) newValue.getUserData();
        });

        HBox hbox = new HBox();
        hbox.getChildren().addAll(pdfRadio, docxRadio, txtRadio);
        hbox.setSpacing(16);

        this.getChildren().addAll(hbox);
    }

    @Override
    public Map<String, Object> getConfig() {
        Map<String, Object> configMap = new HashMap<String, Object>();
        configMap.put("outputFormat", outputFormat);
        return configMap;
    }
}
