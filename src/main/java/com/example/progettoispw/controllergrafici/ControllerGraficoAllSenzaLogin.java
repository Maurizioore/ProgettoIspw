package com.example.progettoispw.controllergrafici;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import java.net.URL;
import java.util.ResourceBundle;


public class ControllerGraficoAllSenzaLogin  extends  ControllerGraficoSenzaAccesso {
    @FXML
    private JFXButton ritornaHomeButton=new JFXButton();

    /*questa classe è associata alla page aiutoAccess chiSiamoNoi ContattaciPage, cosi come la login page che
    * ha pulsanti non in comune con nessuna schermata, gestisce i pulsanti che ha invece in comune grazie a
    * un istanza di controllerGraficoSenzaAccesso, questa gestisce invece tutti i pulsanti che sono in comune usando
    * anche essa ovviamente un istanza di controllerGraficoSenzaAcceso  */

    //questa ce lho aggiunta ora con il singleton
    private final ControllerVisualizzatoreScene controllerVisualizzatoreScene=ControllerVisualizzatoreScene.getInstance(null);
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*questo ritornaHomeButton è una duplicazione di un codice che si torva anche in ControllerGraficoLoginPage
        * in questo caso ho preferito duplicare codice gia esistente poiché è un button in comune alle schermate
        * secondarie ma non alla principale, ovviamente se si dovesse apportare qualche modifica qui bisognerà fare
        * il duale nel ControllerGraficoLoginPage */
        ritornaHomeButton.setOnMouseClicked(event->{
            try {
                //questa di visualizzare home si ripete tante volte, potrei provarlo a metterlo in una classe a parte
                controllerVisualizzatoreScene.visualizzaScena("prova-home.fxml");
            }catch(Exception e){
                System.out.println("error: "+ e);
            }
        });
        super.initialize(url,resourceBundle);
    }
}
