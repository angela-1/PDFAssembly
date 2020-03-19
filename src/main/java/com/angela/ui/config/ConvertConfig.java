package com.angela.ui.config;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ConvertConfig extends VBox {
    public ConvertConfig() {
        final ToggleGroup group = new ToggleGroup();
        RadioButton pdfRadio = new RadioButton("PDF(*.pdf)");
        RadioButton docxRadio = new RadioButton("Word(*.docx)");
        RadioButton txtRadio = new RadioButton("文本文件(*.txt)");
        pdfRadio.setToggleGroup(group);
        pdfRadio.setSelected(true);
        docxRadio.setToggleGroup(group);
        txtRadio.setToggleGroup(group);

        HBox hbox = new HBox();
        hbox.getChildren().addAll(pdfRadio, docxRadio, txtRadio);
        hbox.setSpacing(16);

        this.getChildren().addAll(hbox);
    }
}
