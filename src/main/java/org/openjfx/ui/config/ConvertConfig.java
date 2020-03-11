package org.openjfx.ui.config;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXRadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ConvertConfig extends VBox {
    public ConvertConfig() {
        final ToggleGroup group = new ToggleGroup();
        JFXRadioButton pdfRadio = new JFXRadioButton("PDF(*.pdf)");
        JFXRadioButton docxRadio = new JFXRadioButton("Word(*.docx)");
        JFXRadioButton txtRadio = new JFXRadioButton("文本文件(*.txt)");
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
