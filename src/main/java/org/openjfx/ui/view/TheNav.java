package org.openjfx.ui.view;

import com.jfoenix.controls.JFXListView;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class TheNav extends JFXListView<Label> {

    private final SimpleStringProperty currentPage;

    public static String getLabel(String pageId) {
        String str;
        switch (pageId) {
            case "merge":
                str = "合并文件";
                break;
            case "convert":
                str = "转换文件";
                break;
            case "toc":
                str = "提取目录";
                break;
            case "pagenumber":
                str = "添加页码";
                break;
            default:
                str = "合并文件";
                break;
        }
        return str;
    }

    private ImageView getImage(String pic) {
        String mergeUrl = getClass().getResource(pic).toExternalForm();
        ImageView icon = new ImageView(mergeUrl);
        icon.setFitWidth(20);
        icon.setPreserveRatio(true);
        return icon;
    }

    public TheNav() {
        getStyleClass().add("mylistview");
        currentPage = new SimpleStringProperty(this, "currentPage", "merge");

        Label mergeLabel = new Label(getLabel("merge"));
        mergeLabel.setId("merge");
        mergeLabel.setGraphic(getImage("/img/merge.png"));

        Label convertLabel = new Label(getLabel("convert"));
        convertLabel.setId("convert");
        convertLabel.setGraphic(getImage("/img/convert.png"));

        Label tocLabel = new Label(getLabel("toc"));
        tocLabel.setId("toc");
        tocLabel.setGraphic(getImage("/img/toc.png"));

        Label pageNumberLabel = new Label(getLabel("pagenumber"));
        pageNumberLabel.setId("pagenumber");
        pageNumberLabel.setGraphic(getImage("/img/pagenumber.png"));

        setPadding(new Insets(16, 0, 0, 0));
        getItems().addAll(mergeLabel, convertLabel, tocLabel, pageNumberLabel);
        getSelectionModel().select(0);
        setOnMouseClicked(mouseEvent -> {
            currentPage.set(getSelectionModel().getSelectedItem().getId());
            System.out.println("change to page " + getCurrentPage());
        });
    }

    public String getCurrentPage() {
        return currentPage.get();
    }

    public SimpleStringProperty currentPageProperty() {
        return currentPage;
    }

}
