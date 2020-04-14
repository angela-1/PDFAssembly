package com.angela.ui.view;

import com.angela.Context;
import com.angela.Dispatcher;
import com.angela.task.MyTask;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Map;

public class Frame extends BorderPane {

    private double x = 0.00;
    private double y = 0.00;
    private double width = 0.00;
    private double height = 0.00;
    private final boolean isMax = false;
    private boolean isRight;// 是否处于右边界调整窗口状态
    private boolean isBottomRight;// 是否处于右下角调整窗口状态
    private boolean isBottom;// 是否处于下边界调整窗口状态
    private final double RESIZE_WIDTH = 5.00;
    private final double MIN_WIDTH = 600.00;
    private final double MIN_HEIGHT = 400.00;
    private double xOffset = 0, yOffset = 0;//自定义dialog移动横纵坐标


    private final Context context;

    public Frame(Stage stage) {
        context = new Context();

        TitleBar titleBar = new TitleBar();
        Button closeButton = titleBar.getCloseButton();
        closeButton.setOnAction(event -> stage.close());

        titleBar.selectedProperty().bind(context.taskPropProperty());

        Nav nav = new Nav();
        nav.setMaxWidth(220);
        nav.setMinWidth(160);
        context.taskPropProperty().bind(nav.selectedProperty());

        VBox contentVbox = new VBox();
        contentVbox.setStyle("-fx-background-color: #fff;");
        Source source = new Source();
        context.sourcePropProperty().bind(source.sourceProperty());

        Content content = new Content();
        content.selectedProperty().bind(nav.selectedProperty());

        Action action = new Action();
        action.getRunButton().disableProperty().bind(source.sourceProperty().emptyProperty());
        action.getRunButton().setOnMouseClicked(mouseEvent -> {
            Map<String, Object> configMap = content.getConfig();
            System.out.println("click action");
            try {
                // 打开Word应用程序
                ActiveXComponent app = new ActiveXComponent("KWPS.Application");
                // 设置Word不可见
                app.setProperty("Visible", new Variant(false));
                // 禁用宏
                app.setProperty("AutomationSecurity", new Variant(3));
                // 获得Word中所有打开的文档，返回documents对象
                Dispatch docs = app.getProperty("Documents").toDispatch();
                // 调用Documents对象中Open方法打开文档，并返回打开的文档对象Document
                Dispatch doc = Dispatch.call(docs, "Open", "d:\\word-file.docx", false, true).toDispatch();
                /***
                 * 
                 * 调用Document对象的SaveAs方法，将文档保存为pdf格式
                 * 
                 * Dispatch.call(doc, "SaveAs", pdfFile, wdFormatPDF word保存为pdf格式宏，值为17 )
                 * 
                 */
                Dispatch.call(doc, "ExportAsFixedFormat", "d:\\cal.pdf", 17);// word保存为pdf格式宏，值为17
                // 关闭文档

                Dispatch.call(doc, "Close", false);
                // 关闭Word应用程序
                app.invoke("Quit", 0);
            } catch (Exception e) {
                // TODO: handle exception
            }


            context.setConfig(configMap);
            MyTask task = Dispatcher.run(context);
            new Thread(task).start();
            action.getProgressBar().progressProperty().bind(task.progressProperty());
            action.textPropProperty().bind(task.messageProperty());
        });

        Separator separator = new Separator();

        contentVbox.getChildren().addAll(titleBar, source, content, separator, action);

        setLeft(nav);
        setCenter(contentVbox);

        stage.xProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && !isMax) {
                x = newValue.doubleValue();
            }
        });
        stage.yProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && !isMax) {
                y = newValue.doubleValue();
            }
        });
        stage.widthProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && !isMax) {
                width = newValue.doubleValue();
            }
        });
        stage.heightProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && !isMax) {
                height = newValue.doubleValue();
            }
        });

        contentVbox.setOnMouseMoved((MouseEvent event) -> {
            event.consume();
            double x = event.getSceneX();
            double y = event.getSceneY();
            double width = stage.getWidth();
            double height = stage.getHeight();
            Cursor cursorType = Cursor.DEFAULT;// 鼠标光标初始为默认类型，若未进入调整窗口状态，保持默认类型
            // 先将所有调整窗口状态重置
            isRight = isBottomRight = isBottom = false;
            if (y >= height - RESIZE_WIDTH) {
                if (x <= RESIZE_WIDTH) {// 左下角调整窗口状态
                    //不处理

                } else if (x >= width - RESIZE_WIDTH) {// 右下角调整窗口状态
                    isBottomRight = true;
                    cursorType = Cursor.SE_RESIZE;
                } else {// 下边界调整窗口状态
                    isBottom = true;
                    cursorType = Cursor.S_RESIZE;
                }
            } else if (x >= width - RESIZE_WIDTH) {// 右边界调整窗口状态
                isRight = true;
                cursorType = Cursor.E_RESIZE;
            }
            // 最后改变鼠标光标
            setCursor(cursorType);
        });

        contentVbox.setOnMouseDragged((MouseEvent event) -> {

            //根据鼠标的横纵坐标移动dialog位置
            event.consume();
            if (yOffset != 0) {
                stage.setX(event.getScreenX() - xOffset);
                if (event.getScreenY() - yOffset < 0) {
                    stage.setY(0);
                } else {
                    stage.setY(event.getScreenY() - yOffset);
                }
            }

            double x = event.getSceneX();
            double y = event.getSceneY();
            // 保存窗口改变后的x、y坐标和宽度、高度，用于预判是否会小于最小宽度、最小高度
            double nextX = stage.getX();
            double nextY = stage.getY();
            double nextWidth = stage.getWidth();
            double nextHeight = stage.getHeight();
            if (isRight || isBottomRight) {// 所有右边调整窗口状态
                nextWidth = x;
            }
            if (isBottomRight || isBottom) {// 所有下边调整窗口状态
                nextHeight = y;
            }
            if (nextWidth <= MIN_WIDTH) {// 如果窗口改变后的宽度小于最小宽度，则宽度调整到最小宽度
                nextWidth = MIN_WIDTH;
            }
            if (nextHeight <= MIN_HEIGHT) {// 如果窗口改变后的高度小于最小高度，则高度调整到最小高度
                nextHeight = MIN_HEIGHT;
            }
            // 最后统一改变窗口的x、y坐标和宽度、高度，可以防止刷新频繁出现的屏闪情况
            stage.setX(nextX);
            stage.setY(nextY);
            stage.setWidth(nextWidth);
            stage.setHeight(nextHeight);

        });
        //鼠标点击获取横纵坐标
        contentVbox.setOnMousePressed(event -> {
            event.consume();
            xOffset = event.getSceneX();
            if (event.getSceneY() > 46) {
                yOffset = 0;
            } else {
                yOffset = event.getSceneY();
            }
        });
        //根据鼠标移动的位置改变鼠标的样式
//		root.setOnMouseMoved(event -> {
//			event.consume();
//			if (event.getSceneY() > 46) {
//				root.getStyleClass().removeAll("sursor-move");
//			} else {
//				root.getStyleClass().add("sursor-move");
//			}
//		});

    }
}
