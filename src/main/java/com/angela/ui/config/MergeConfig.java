package com.angela.ui.config;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.Map;

public class MergeConfig extends VBox implements MyConfig {

    private final SimpleBooleanProperty addWhitePageProp;
    private final SimpleBooleanProperty keepBookmarkProp;
    private final SimpleBooleanProperty samePageProp;


    public MergeConfig() {
        this.addWhitePageProp = new SimpleBooleanProperty(false);
        this.keepBookmarkProp = new SimpleBooleanProperty(true);
        this.samePageProp = new SimpleBooleanProperty(false);

        CheckBox addWhitePage = new CheckBox("若页数为单数则添加一个空白页");
        addWhitePage.selectedProperty().bindBidirectional(this.addWhitePageProp);

        CheckBox bookmark = new CheckBox("保留原书签");
        bookmark.selectedProperty().bindBidirectional(this.keepBookmarkProp);

        CheckBox samePage = new CheckBox("均一化页面大小");
        samePage.selectedProperty().bindBidirectional(this.samePageProp);

        this.setSpacing(8);
        this.getChildren().addAll(addWhitePage, bookmark, samePage);

    }

    @Override
    public Map<String, Object> getConfig() {
        Map<String, Object> configMap = new HashMap<String, Object>();
        configMap.put("addWhitePageProp", addWhitePageProp.getValue());
        configMap.put("keepBookmarkProp", keepBookmarkProp.getValue());
        configMap.put("samePageProp", samePageProp.getValue());
        return configMap;
    }
}
