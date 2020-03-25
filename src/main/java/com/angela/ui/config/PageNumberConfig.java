package com.angela.ui.config;

import com.angela.task.pagenumber.NumberPos;
import com.angela.task.pagenumber.NumberStyle;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.Map;

public class PageNumberConfig extends VBox implements MyConfig {

    private NumberStyle numberStyle = NumberStyle.Normal;
    private NumberPos numberPos = NumberPos.Center;

    public PageNumberConfig() {
        final ToggleGroup group = new ToggleGroup();
        RadioButton normalRadio = new RadioButton("普通(1,2,3)");
        normalRadio.setUserData(NumberStyle.Normal);

        RadioButton collectionRadio = new RadioButton("汇编(001,002,003)");
        collectionRadio.setUserData(NumberStyle.Collection);

        RadioButton totalRadio = new RadioButton("总数(1/20,2/20,3/20)");
        totalRadio.setUserData(NumberStyle.Total);

        RadioButton decoratorRadio = new RadioButton("装饰(—1—,—2—,—3—)");
        decoratorRadio.setUserData(NumberStyle.Decorator);

        normalRadio.setToggleGroup(group);
        normalRadio.setSelected(true);
        totalRadio.setToggleGroup(group);
        decoratorRadio.setToggleGroup(group);
        collectionRadio.setToggleGroup(group);

        group.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("pagenumber style group listener " + newValue.getUserData());
            numberStyle = (NumberStyle) newValue.getUserData();
        });

        GridPane gridPane = new GridPane();
        gridPane.setVgap(16);
        gridPane.setHgap(16);

        gridPane.add(normalRadio, 0, 0);
        gridPane.add(collectionRadio, 1, 0);
        gridPane.add(totalRadio, 0, 1);
        gridPane.add(decoratorRadio, 1, 1);

        final ToggleGroup posGroup = new ToggleGroup();
        RadioButton centerPos = new RadioButton("居中");
        centerPos.setUserData(NumberPos.Center);

        RadioButton cornerPos = new RadioButton("奇偶页左右");
        cornerPos.setUserData(NumberPos.Corner);

        RadioButton sidePos = new RadioButton("页边");
        sidePos.setUserData(NumberPos.Side);

        centerPos.setToggleGroup(posGroup);
        centerPos.setSelected(true);
        cornerPos.setToggleGroup(posGroup);
        sidePos.setToggleGroup(posGroup);

        posGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("pagenumber pos group listener " + newValue.getUserData());
            numberPos = (NumberPos) newValue.getUserData();
        });
        HBox posHbox = new HBox();
        posHbox.getChildren().addAll(centerPos, cornerPos, sidePos);
        posHbox.setSpacing(16);

        Label style = new Label("样式");
        Label pos = new Label("位置");

        this.setSpacing(16);
        this.getChildren().addAll(style, gridPane, pos, posHbox);
    }

    @Override
    public Map<String, Object> getConfig() {
        Map<String, Object> configMap = new HashMap<String, Object>();
        configMap.put("style", numberStyle);
        configMap.put("pos", numberPos);
        return configMap;
    }
}
