package com.angela.task.pagenumber;


import com.angela.domain.NumberPos;
import com.angela.domain.NumberStyle;

public class NumberFactory {
    public static MyStyle getNumberStyle(NumberStyle numberStyle) {
        MyStyle style = null;
        switch (numberStyle) {
            case Normal:
                style = new NormalStyle();
                break;
            case Collection:
                style = new CollectionStyle();
                break;
            case Total:
                style = new TotalStyle();
                break;
            case Decorator:
                style = new DecoratorStyle();
                break;
            default:
                style = new NormalStyle();
        }
        return style;
    }

    public static MyPos getNumberPos(NumberPos numberPos) {
        MyPos pos = null;
        switch (numberPos) {
            case Center:
                pos = new CenterPos();
                break;
            case Corner:
                pos = new CornerPos();
                break;
            case Side:
                pos = new SidePos();
                break;
            default:
                pos = new CenterPos();
        }
        return pos;
    }
}
