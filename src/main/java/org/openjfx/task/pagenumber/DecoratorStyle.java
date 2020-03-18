package org.openjfx.task.pagenumber;

public class DecoratorStyle implements MyStyle {
    @Override
    public String getName() {
        return "装饰";
    }

    @Override
    public String getNumberString(int page, int total) {
        return "— " + page + " —";
    }
}
