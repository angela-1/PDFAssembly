module hellofx {
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.controls;
    requires com.jfoenix;
    requires kernel;
    requires layout;
    opens org.openjfx to javafx.graphics;
    exports org.openjfx;
}