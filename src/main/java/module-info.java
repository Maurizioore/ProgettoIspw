module com.example.progettoispw {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires java.sql;


    opens com.example.progettoispw.controllergrafici to javafx.fxml;
    exports com.example.progettoispw.controllergrafici;
}