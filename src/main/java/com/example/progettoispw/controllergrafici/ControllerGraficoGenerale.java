package com.example.progettoispw.controllergrafici;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerGraficoGenerale extends ControllerGraficoSenzaAccesso{
    @FXML
    private JFXButton ritornaHomeButton;

    private final ControllerVisualizzatoreScene controllerVisualizzatoreScene=ControllerVisualizzatoreScene.getInstance(null);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ritornaHomeButton.setOnMouseClicked(event->{
            try {
                controllerVisualizzatoreScene.visualizzaScena("prova-home.fxml");
            }catch(Exception e){
                System.exit(-1);
            }
        });
        //questo avrà i suoi pulsanti e alla fine chiamerà il super di quelli in comune, per non darli al figlio
        //quelli che non sono in comune dovrei farli private
        super.initialize(url,resourceBundle);
    }
}
