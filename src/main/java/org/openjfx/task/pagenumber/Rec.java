package org.openjfx.task.pagenumber;

public class Rec {
    float x;
    float y;
    float width;
    float height;

    public Rec(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public float getPointX() {
        return x + width / 2;
    }

    public float getPointY() {
        return y + height / 2;
    }
}
