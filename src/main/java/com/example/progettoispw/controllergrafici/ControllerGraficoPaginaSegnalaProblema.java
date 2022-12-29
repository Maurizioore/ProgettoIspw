package com.example.progettoispw.controllergrafici;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerGraficoPaginaSegnalaProblema extends ControllerGraficoLoginPage {

    /*questo controller è associato alla pagina dei segnala problemi, anche questa ha i pulsanti in comune
    * nella barra del menu e quindi anche questa riuserà il codice di quei pulsanti che sono stati gia' definiti
    * inoltre implementerà la logica dei suoi button che non ha in comune con le altre view */
    //inizio implementando la logica dei buttonComuni
    @FXML
    private JFXButton ritornaHomeButton;
    //aggiunta ora con il singleton
    private final ControllerVisualizzatoreScene controllerVisualizzatoreScene=ControllerVisualizzatoreScene.getInstance(null);
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //ritornaHomeButton.setOnMouseClicked(event->{
        //    try {
        //        controllerVisualizzatoreScene.visualizzaScena("prova-home.fxml");
        //    }catch(Exception e){
        //        System.out.println("error: "+ e);
        //    }
        //});
        super.initialize(url,resourceBundle);
    }
}