module com.example.progettoispw {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;


    opens com.example.progettoispw.controllerGrafici to javafx.fxml;
    exports com.example.progettoispw.controllerGrafici;
}