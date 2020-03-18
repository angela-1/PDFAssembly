package org.openjfx.task.pagenumber;

public class TotalStyle implements MyStyle{
    @Override
    public String getName() {
        return "总数";
    }

    @Override
    public String getNumberString(int page, int total) {
        return page + "/" + total;
    }
}
