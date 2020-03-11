package org.openjfx.ui.config;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXRadioButton;
import javafx.geometry.Insets;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TocConfig extends VBox {
    public TocConfig() {
        final ToggleGroup group = new ToggleGroup();
        JFXRadioButton xlsxRadio = new JFXRadioButton("Excel(*.xlsx)");
        JFXRadioButton docxRadio = new JFXRadioButton("Word(*.docx)");
        JFXRadioButton txtRadio = new JFXRadioButton("文本文件(*.txt)");
        xlsxRadio.setToggleGroup(group);
        xlsxRadio.setSelected(true);
        docxRadio.setToggleGroup(group);
        txtRadio.setToggleGroup(group);

        HBox hbox = new HBox();
        hbox.getChildren().addAll(xlsxRadio, docxRadio, txtRadio);
        hbox.setSpacing(16);

        this.getChildren().addAll(hbox);
    }
}
