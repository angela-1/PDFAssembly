package org.openjfx.task.pagenumber;

public class NormalStyle implements MyStyle {
    @Override
    public String getName() {
        return "普通";
    }

    @Override
    public String getNumberString(int page, int total) {
        return String.valueOf(page);
    }
}
