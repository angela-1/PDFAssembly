package org.openjfx.task.pagenumber;

import org.openjfx.domain.NumberPos;
import org.openjfx.domain.NumberStyle;

public class NumberFactory {
    public static MyStyle getNumberStyle(NumberStyle numberStyle) {
        return switch (numberStyle) {
            case Normal -> new NormalStyle();
            case Collection -> new CollectionStyle();
            case Total -> new TotalStyle();
            case Decorator -> new DecoratorStyle();
        };
    }

    public static MyPos getNumberPos(NumberPos numberPosu) {
        return switch (numberPosu) {
            case Center -> new CenterPos();
            case Corner -> new CornerPos();
            case Side -> new SidePos();
        };
    }
}
