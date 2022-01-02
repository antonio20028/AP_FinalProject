module iiitd.ac.ap_group17.willhero {
    requires javafx.controls;
    requires javafx.fxml;

    requires  javafx.graphics;
    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires java.desktop;

    opens iiitd.ac.ap_group17.willhero to javafx.fxml;
    exports iiitd.ac.ap_group17.willhero;
}