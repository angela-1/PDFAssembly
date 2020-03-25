package com.angela.ui.config;

import com.angela.task.toc.TocFormat;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.Map;

public class TocConfig extends VBox implements MyConfig {

    private TocFormat outputFormat;

    public TocConfig() {
        outputFormat = TocFormat.Xlsx;

        final ToggleGroup group = new ToggleGroup();
        RadioButton xlsxRadio = new RadioButton("Excel(*.xlsx)");
        xlsxRadio.setUserData(TocFormat.Xlsx);
        RadioButton docxRadio = new RadioButton("Word(*.docx)");
        docxRadio.setUserData(TocFormat.Docx);
        RadioButton txtRadio = new RadioButton("文本文件(*.txt)");
        txtRadio.setUserData(TocFormat.Txt);

        xlsxRadio.setToggleGroup(group);
        xlsxRadio.setSelected(true);
        docxRadio.setToggleGroup(group);
        txtRadio.setToggleGroup(group);

        group.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("group listener " + newValue.getUserData().toString());
            outputFormat = (TocFormat) newValue.getUserData();
        });

        HBox hbox = new HBox();
        hbox.getChildren().addAll(xlsxRadio, docxRadio, txtRadio);
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
