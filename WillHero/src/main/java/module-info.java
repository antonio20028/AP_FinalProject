module iiitd.ac.ap_group17.willhero {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;

    opens iiitd.ac.ap_group17.willhero to javafx.fxml;
    exports iiitd.ac.ap_group17.willhero;
}