module com.example.contactapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.contactapp to javafx.fxml;
    exports com.example.contactapp;
}