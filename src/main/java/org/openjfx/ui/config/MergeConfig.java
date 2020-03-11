package org.openjfx.ui.config;

import com.jfoenix.controls.JFXCheckBox;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;

public class MergeConfig extends VBox {
    public MergeConfig() {
        JFXCheckBox oddWhitePage = new JFXCheckBox("若页数为单数则添加一个空白页");
        JFXCheckBox bookmark = new JFXCheckBox("保留原书签");
        JFXCheckBox samePage = new JFXCheckBox("均一化页面大小");
        this.setSpacing(8);
        this.getChildren().addAll(oddWhitePage, bookmark, samePage);
    }
}
