module com.example.progettoispw {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires java.sql;
    requires software.amazon.awssdk.regions;
    requires software.amazon.awssdk.services.secretsmanager;


    opens com.example.progettoispw.controllergrafici to javafx.fxml;
    exports com.example.progettoispw.controllergrafici;
}