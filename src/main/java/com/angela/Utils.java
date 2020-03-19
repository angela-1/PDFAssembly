package com.angela;

import javafx.scene.image.ImageView;

public class Utils {
    public static ImageView getImageView(String imagePath, int width) {
        String mergeUrl = Utils.class.getResource(imagePath).toExternalForm();
        ImageView imageView = new ImageView(mergeUrl);
        imageView.setFitWidth(width);
        imageView.setPreserveRatio(true);
        return imageView;
    }

    public static String getTitle(String pageId) {
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
}
