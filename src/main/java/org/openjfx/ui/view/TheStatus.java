package org.openjfx.ui.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class TheStatus extends VBox {
    private final SimpleStringProperty textProp;

    public TheStatus() {
        textProp = new SimpleStringProperty("就绪");
        Label status = new Label();
        status.textProperty().bind(textProp);
        getChildren().add(status);
        setAlignment(Pos.CENTER_LEFT);
    }

    public String getTextProp() {
        return textProp.get();
    }

    public SimpleStringProperty textPropProperty() {
        return textProp;
    }

    public void setTextProp(String textProp) {
        this.textProp.set(textProp);
    }
}
