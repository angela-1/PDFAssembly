package org.openjfx.ui.config;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXRadioButton;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.Map;

public class TocConfig extends VBox implements MyConfig {

    private String outputFormat;

    public TocConfig() {
        outputFormat = "xlsx";

        final ToggleGroup group = new ToggleGroup();
        JFXRadioButton xlsxRadio = new JFXRadioButton("Excel(*.xlsx)");
        xlsxRadio.setUserData("xlsx");
        JFXRadioButton docxRadio = new JFXRadioButton("Word(*.docx)");
        docxRadio.setUserData("docx");
        JFXRadioButton txtRadio = new JFXRadioButton("文本文件(*.txt)");
        txtRadio.setUserData("txt");

        xlsxRadio.setToggleGroup(group);
        xlsxRadio.setSelected(true);
        docxRadio.setToggleGroup(group);
        txtRadio.setToggleGroup(group);

        group.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("group listener " + newValue.getUserData().toString());
            outputFormat = (String) newValue.getUserData();
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
