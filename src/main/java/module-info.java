module hellofx {
    requires com.itextpdf;
    requires javafx.base;
    requires javafx.controls;
    requires javafx.graphics;
    opens org.openjfx to javafx.graphics;
    requires com.jfoenix;
    exports org.openjfx;
}

