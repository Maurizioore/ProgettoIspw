module com.example.progettoispw {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.progettoispw to javafx.fxml;
    exports com.example.progettoispw;
}