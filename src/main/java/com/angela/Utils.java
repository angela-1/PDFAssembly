package com.angela;

import javafx.scene.image.ImageView;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    public static List<String> filter(List<String> list) {
        Set<String> supportFormat = new HashSet<>();
        supportFormat.add("pdf");
        supportFormat.add("png");
        supportFormat.add("jpeg");
        supportFormat.add("jpg");

        return list.stream().filter(v -> {
            Path f = Paths.get(v);
            if (Files.isDirectory(f)) {
                return true;
            } else {
                String filename = f.getFileName().toString();
                int point = filename.lastIndexOf(".");
                String extension = filename.substring(point + 1);
                System.out.println("filter" + f + extension.toLowerCase());
                return supportFormat.contains(extension.toLowerCase());
            }
        }).collect(Collectors.toList());
    }
}
