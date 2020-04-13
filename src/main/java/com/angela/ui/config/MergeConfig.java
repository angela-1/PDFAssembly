package com.angela.ui.config;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.Map;

public class MergeConfig extends VBox implements MyConfig {

    private final SimpleBooleanProperty addWhitePageProp;
    private final SimpleBooleanProperty keepBookmarkProp;


    public MergeConfig() {
        this.addWhitePageProp = new SimpleBooleanProperty(false);
        this.keepBookmarkProp = new SimpleBooleanProperty(false);

        CheckBox bookmark = new CheckBox("保留原书签");
        bookmark.selectedProperty().bindBidirectional(this.keepBookmarkProp);

        CheckBox addWhitePage = new CheckBox("若页数为单数则添加一个空白页");
        addWhitePage.selectedProperty().bindBidirectional(this.addWhitePageProp);

        this.setSpacing(8);
        this.getChildren().addAll(bookmark, addWhitePage);

    }

    @Override
    public Map<String, Object> getConfig() {
        Map<String, Object> configMap = new HashMap<String, Object>();
        configMap.put("addWhitePageProp", addWhitePageProp.getValue());
        configMap.put("keepBookmarkProp", keepBookmarkProp.getValue());
        return configMap;
    }
}
