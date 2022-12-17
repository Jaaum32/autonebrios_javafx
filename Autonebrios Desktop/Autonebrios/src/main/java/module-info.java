module com.example.autonebrios {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.autonebrios to javafx.fxml;
    exports com.example.autonebrios;
}