module com.example.colorchooser {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.colorchooser to javafx.fxml;
    exports com.example.colorchooser;
}