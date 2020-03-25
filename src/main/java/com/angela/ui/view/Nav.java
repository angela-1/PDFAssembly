package com.angela.ui.view;

import com.angela.App;
import com.angela.Utils;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class Nav extends VBox {
    private final SimpleStringProperty selected;

    public Nav() {
        setPadding(new Insets(16, 0, 0, 0));
        setStyle("-fx-background-color: #e5e5e5;");
        setPrefWidth(180);

        selected = new SimpleStringProperty("merge");

        Label mergeLabel = new Label(Utils.getTitle("merge"));
        mergeLabel.setId("merge");
        mergeLabel.setGraphic(Utils.getImageView("/image/merge.png", 20));

        Label tocLabel = new Label(Utils.getTitle("toc"));
        tocLabel.setId("toc");
        tocLabel.setGraphic(Utils.getImageView("/image/toc.png", 20));

        Label pageNumberLabel = new Label(Utils.getTitle("pagenumber"));
        pageNumberLabel.setId("pagenumber");
        pageNumberLabel.setGraphic(Utils.getImageView("/image/pagenumber.png", 20));

        ListView<Label> navList = new ListView<>();

        navList.getItems().addAll(mergeLabel, tocLabel, pageNumberLabel);
        navList.getStyleClass().add("my-list-view");

        navList.getSelectionModel().select(0);
        navList.setOnMouseClicked(mouseEvent -> {
            String id = navList.getSelectionModel().getSelectedItem().getId();
            selected.set(id);
            System.out.println("nav to " + id);
        });

        VBox white = new VBox();

        HBox links = new HBox();
        Button help = new Button();
        help.setGraphic(Utils.getImageView("/image/help.png", 16));
        help.getStyleClass().add("link-button");
        help.setOnAction(actionEvent -> {
            String readme = getClass().getResource("/README.html").toExternalForm();
            App.getInstance().getHostServices().showDocument(readme);
        });

        Button github = new Button();
        github.setGraphic(Utils.getImageView("/image/github.png", 16));
        github.getStyleClass().add("link-button");
        github.setOnAction(actionEvent -> {
            String repo = "https://github.com/angela-1/PDFAssembly";
            App.getInstance().getHostServices().showDocument(repo);
        });

        links.getChildren().addAll(help, github);
        white.getChildren().add(links);

        getChildren().addAll(navList, white);
        VBox.setVgrow(navList, Priority.ALWAYS);
    }

    public String getSelected() {
        return selected.get();
    }

    public SimpleStringProperty selectedProperty() {
        return selected;
    }
}
