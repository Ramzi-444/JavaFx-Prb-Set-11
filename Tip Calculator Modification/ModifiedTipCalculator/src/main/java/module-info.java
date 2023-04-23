module com.example.modifiedtipcalculator {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.modifiedtipcalculator to javafx.fxml;
    exports com.example.modifiedtipcalculator;
}