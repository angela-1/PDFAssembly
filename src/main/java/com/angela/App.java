package com.angela;

import com.angela.ui.view.Frame;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * JavaFX App
 */
public class App extends Application {
    private static App mInstance;

    public static App getInstance() {
        return mInstance;
    }

//    private double x = 0.00;
//    private double y = 0.00;
//    private double width = 0.00;
//    private double height = 0.00;
//    private boolean isMax = false;
//    private boolean isRight;// 是否处于右边界调整窗口状态
//    private boolean isBottomRight;// 是否处于右下角调整窗口状态
//    private boolean isBottom;// 是否处于下边界调整窗口状态
//    private double RESIZE_WIDTH = 5.00;
//    private double MIN_WIDTH = 400.00;
//    private double MIN_HEIGHT = 300.00;
//    private double xOffset = 0, yOffset = 0;//自定义dialog移动横纵坐标
//
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        primaryStage.initStyle(StageStyle.TRANSPARENT);
//        BorderPane root = new BorderPane();
//        GridPane gpTitle = new GridPane();
//        gpTitle.setAlignment(Pos.CENTER_LEFT);
//        gpTitle.setPadding(new Insets(10));
//        Label lbTitle = new Label("自定义窗口");
//        lbTitle.setGraphic(new ImageView("/image/github.png"));
//        Button btnMin = new Button("最小化");
//        Button btnMax = new Button("最大化");
//        Button btnClose = new Button();
//
//        btnClose.setGraphic(new ImageView("/image/close.png"));
//
//        gpTitle.add(lbTitle, 0 , 0);
//        gpTitle.add(btnMin, 1, 0);
//        gpTitle.add(btnMax, 2, 0);
//        gpTitle.add(btnClose, 3, 0);
//        GridPane.setHgrow(lbTitle, Priority.ALWAYS);
//        root.setTop(gpTitle);
//        Label lbTips = new Label("Welcome to learn javafx!!!");
//        lbTips.setFont(Font.font(20));
//        lbTips.setTextFill(Paint.valueOf("red"));
//        root.setCenter(lbTips);
//        root.setStyle("-fx-background-color: white ;-fx-border-color: rgb(128,128,64); -fx-border-width: 0;");
//        gpTitle.setStyle("-fx-background-color: rgb(58.0,154.0,242.0);");
//
//        btnMin.setOnAction(new EventHandler<ActionEvent>() {
//
//            @Override
//            public void handle(ActionEvent event) {
//                primaryStage.setIconified(true);
//            }
//        });
//        btnMax.setOnAction(new EventHandler<ActionEvent>() {
//
//            @Override
//            public void handle(ActionEvent event) {
//                Rectangle2D rectangle2d = Screen.getPrimary().getVisualBounds();
//                isMax = !isMax;
//                if (isMax) {
//                    // 最大化
//                    primaryStage.setX(rectangle2d.getMinX());
//                    primaryStage.setY(rectangle2d.getMinY());
//                    primaryStage.setWidth(rectangle2d.getWidth());
//                    primaryStage.setHeight(rectangle2d.getHeight());
//                } else {
//                    // 缩放回原来的大小
//                    primaryStage.setX(x);
//                    primaryStage.setY(y);
//                    primaryStage.setWidth(width);
//                    primaryStage.setHeight(height);
//                }
//            }
//        });
//        btnClose.setOnAction(new EventHandler<ActionEvent>() {
//
//            @Override
//            public void handle(ActionEvent event) {
//                primaryStage.close();
//            }
//        });
//        primaryStage.xProperty().addListener(new ChangeListener<Number>() {
//
//            @Override
//            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//                if (newValue != null && !isMax) {
//                    x = newValue.doubleValue();
//                }
//            }
//        });
//        primaryStage.yProperty().addListener(new ChangeListener<Number>() {
//
//            @Override
//            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//                if (newValue != null && !isMax) {
//                    y = newValue.doubleValue();
//                }
//            }
//        });
//        primaryStage.widthProperty().addListener(new ChangeListener<Number>() {
//
//            @Override
//            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//                if (newValue != null && !isMax) {
//                    width = newValue.doubleValue();
//                }
//            }
//        });
//        primaryStage.heightProperty().addListener(new ChangeListener<Number>() {
//
//            @Override
//            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//                if (newValue != null && !isMax) {
//                    height = newValue.doubleValue();
//                }
//            }
//        });
//
//        root.setOnMouseMoved((MouseEvent event) -> {
//            event.consume();
//            double x = event.getSceneX();
//            double y = event.getSceneY();
//            double width = primaryStage.getWidth();
//            double height = primaryStage.getHeight();
//            Cursor cursorType = Cursor.DEFAULT;// 鼠标光标初始为默认类型，若未进入调整窗口状态，保持默认类型
//            // 先将所有调整窗口状态重置
//            isRight = isBottomRight = isBottom = false;
//            if (y >= height - RESIZE_WIDTH) {
//                if (x <= RESIZE_WIDTH) {// 左下角调整窗口状态
//                    //不处理
//
//                } else if (x >= width - RESIZE_WIDTH) {// 右下角调整窗口状态
//                    isBottomRight = true;
//                    cursorType = Cursor.SE_RESIZE;
//                } else {// 下边界调整窗口状态
//                    isBottom = true;
//                    cursorType = Cursor.S_RESIZE;
//                }
//            } else if (x >= width - RESIZE_WIDTH) {// 右边界调整窗口状态
//                isRight = true;
//                cursorType = Cursor.E_RESIZE;
//            }
//            // 最后改变鼠标光标
//            root.setCursor(cursorType);
//        });
//
//        root.setOnMouseDragged((MouseEvent event) -> {
//
//            //根据鼠标的横纵坐标移动dialog位置
//            event.consume();
//            if (yOffset != 0 ) {
//                primaryStage.setX(event.getScreenX() - xOffset);
//                if (event.getScreenY() - yOffset < 0) {
//                    primaryStage.setY(0);
//                } else {
//                    primaryStage.setY(event.getScreenY() - yOffset);
//                }
//            }
//
//            double x = event.getSceneX();
//            double y = event.getSceneY();
//            // 保存窗口改变后的x、y坐标和宽度、高度，用于预判是否会小于最小宽度、最小高度
//            double nextX = primaryStage.getX();
//            double nextY = primaryStage.getY();
//            double nextWidth = primaryStage.getWidth();
//            double nextHeight = primaryStage.getHeight();
//            if (isRight || isBottomRight) {// 所有右边调整窗口状态
//                nextWidth = x;
//            }
//            if (isBottomRight || isBottom) {// 所有下边调整窗口状态
//                nextHeight = y;
//            }
//            if (nextWidth <= MIN_WIDTH) {// 如果窗口改变后的宽度小于最小宽度，则宽度调整到最小宽度
//                nextWidth = MIN_WIDTH;
//            }
//            if (nextHeight <= MIN_HEIGHT) {// 如果窗口改变后的高度小于最小高度，则高度调整到最小高度
//                nextHeight = MIN_HEIGHT;
//            }
//            // 最后统一改变窗口的x、y坐标和宽度、高度，可以防止刷新频繁出现的屏闪情况
//            primaryStage.setX(nextX);
//            primaryStage.setY(nextY);
//            primaryStage.setWidth(nextWidth);
//            primaryStage.setHeight(nextHeight);
//
//        });
//        //鼠标点击获取横纵坐标
//        root.setOnMousePressed(event -> {
//            event.consume();
//            xOffset = event.getSceneX();
//            if (event.getSceneY() > 46) {
//                yOffset = 0;
//            } else {
//                yOffset = event.getSceneY();
//            }
//        });
//        //根据鼠标移动的位置改变鼠标的样式
////		root.setOnMouseMoved(event -> {
////			event.consume();
////			if (event.getSceneY() > 46) {
////				root.getStyleClass().removeAll("sursor-move");
////			} else {
////				root.getStyleClass().add("sursor-move");
////			}
////		});
//
//        Scene scene = new Scene(root, 400, 300);
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("自定义窗口");
////        primaryStage.getIcons().add("/merge.png");
//        primaryStage.show();
//    }


//    @Override
//    public void start(Stage primaryStage) {
//        GridPane gridPane = new GridPane();
//        gridPane.setStyle("-fx-background-color: rgb(78.0,163.0,248.0);");
//        gridPane.setPrefHeight(32);
//        gridPane.setAlignment(Pos.CENTER_LEFT);
//        Label label = new Label("title");
//        label.setFont(Font.font(14));
//        label.setTextFill(Paint.valueOf("white"));
//        ImageView imageView = new ImageView("merge.png");
//        imageView.setFitHeight(24);
//        imageView.setFitWidth(24);
//        label.setGraphic(imageView);
//        Button minButton = new Button("—");
//        Button amxButton = new Button("口");
//        Button closeButton = new Button("X");
//        minButton.setStyle("-fx-base: rgb(243,243,243); -fx-border-color: rgb(243,243,243); -fx-border-width: 0.1; "
//                + "-fx-max-height: infinity;-fx-text-fill: white ; -fx-border-image-insets: 0;");
//        amxButton.setStyle("-fx-base: rgb(243,243,243); -fx-border-color: rgb(243,243,243); -fx-border-width: 0.1; "
//                + "-fx-max-height: infinity;-fx-text-fill: white ; -fx-border-image-insets: 0;");
//        closeButton.setStyle("-fx-base: rgb(255,128,128); -fx-border-color: rgb(243,243,243); -fx-border-width: 0.1; "
//                + "-fx-max-height: infinity;-fx-text-fill: white ; -fx-border-image-insets: 0;");
//        minButton.setOnAction(new EventHandler<ActionEvent>() {
//
//            @Override
//            public void handle(ActionEvent event) {
//                primaryStage.setIconified(true);
//            }
//        });
//        amxButton.setOnAction(new EventHandler<ActionEvent>() {
//
//            @Override
//            public void handle(ActionEvent event) {
//                primaryStage.setMaximized(!primaryStage.isMaximized());
//            }
//        });
//        closeButton.setOnAction(new EventHandler<ActionEvent>() {
//
//            @Override
//            public void handle(ActionEvent event) {
//                primaryStage.close();
//            }
//        });
//        gridPane.addColumn(0, label);
//        GridPane.setHgrow(label, Priority.ALWAYS);
//        gridPane.addColumn(1, minButton);
//        gridPane.addColumn(2, amxButton);
//        gridPane.addColumn(3, closeButton);
//        VBox box = new VBox();
//        box.getChildren().add(gridPane);
//        Scene scene = new Scene(box);
////        primaryStage.initStyle(StageStyle.UNDECORATED);
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }

    @Override
    public void start(Stage stage) {
        mInstance = this;
//        MainPane mainPane = new MainPane();
//        var scene = new Scene(mainPane, 640, 480);
        Frame frame = new Frame(stage);
        var scene = new Scene(frame, 640, 480);

        scene.getStylesheets()
                .add(getClass().getResource("/css/style.css").toExternalForm());

        stage.initStyle(StageStyle.UNDECORATED);
        String logoUrl = this.getClass().getResource("/merge.png").toExternalForm();
        stage.getIcons().add(new Image(logoUrl));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}