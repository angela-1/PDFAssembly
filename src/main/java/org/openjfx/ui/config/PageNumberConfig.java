package org.openjfx.ui.config;

import com.jfoenix.controls.JFXRadioButton;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PageNumberConfig extends VBox {
    public PageNumberConfig() {
        final ToggleGroup group = new ToggleGroup();
        JFXRadioButton normalRadio = new JFXRadioButton("普通(1,2,3)");
        JFXRadioButton collectionRadio = new JFXRadioButton("汇编(001,002,003)");
        JFXRadioButton totalRadio = new JFXRadioButton("总数(1/20,2/20,3/20)");
        JFXRadioButton decoratorRadio = new JFXRadioButton("装饰(—1—,—2—,—3—)");
        normalRadio.setToggleGroup(group);
        normalRadio.setSelected(true);
        totalRadio.setToggleGroup(group);
        decoratorRadio.setToggleGroup(group);
        collectionRadio.setToggleGroup(group);

        GridPane gridPane = new GridPane();
        gridPane.setVgap(8);
        gridPane.setHgap(16);

        gridPane.add(normalRadio, 0,0);
        gridPane.add(collectionRadio, 1,0);
        gridPane.add(totalRadio, 0,1);
        gridPane.add(decoratorRadio, 1,1);

        final ToggleGroup posGroup = new ToggleGroup();
        JFXRadioButton centerPos = new JFXRadioButton("居中");
        JFXRadioButton leftRightPos = new JFXRadioButton("奇偶页左右");
        centerPos.setToggleGroup(posGroup);
        centerPos.setSelected(true);
        leftRightPos.setToggleGroup(posGroup);

        HBox posHbox = new HBox();
        posHbox.getChildren().addAll(centerPos, leftRightPos);
        posHbox.setSpacing(16);

        Label style = new Label("样式");
        Label pos = new Label("位置");

        this.setSpacing(8);
        this.getChildren().addAll(style, gridPane, pos, posHbox);
    }
}
