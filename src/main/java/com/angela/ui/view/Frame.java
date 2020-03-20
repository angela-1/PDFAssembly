package com.angela.ui.view;

import javafx.scene.control.SplitPane;
import javafx.scene.layout.VBox;

public class Frame extends SplitPane {

    public Frame() {
        Nav nav = new Nav();
        Content content = new Content();

        VBox leftControl  = new VBox(nav);
        VBox rightControl = new VBox(content);

        getItems().addAll(leftControl, rightControl);
        setDividerPositions(0.2f, 0.8f);

    }
}
