package com.angela.task.pagenumber;

public class CollectionStyle implements MyStyle {
    @Override
    public String getName() {
        return "集合";
    }

    @Override
    public String getNumberString(int page, int total) {
        int len = Math.max(String.valueOf(total).length(), 2);
        return String.format("%0" + len + "d", page);
    }
}
