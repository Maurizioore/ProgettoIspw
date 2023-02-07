package com.example.progettoispw.controllergrafici;

import javafx.fxml.FXML;

public class ControllerGraficoAvvertenzaRegistrazione{
    private final ControllerVisualizzatoreScene controllerVisualizzatoreScene=ControllerVisualizzatoreScene.getInstance(null);
    @FXML
    public void ritornaHome(){
        controllerVisualizzatoreScene.visualizzaScena("prova-home.fxml");
    }
    @FXML
    public void accediButtonClicked(){
        controllerVisualizzatoreScene.visualizzaScena("login-registrazione-page.fxml");
    }
    @FXML
    public void registratiButtonClicked(){
        controllerVisualizzatoreScene.visualizzaScena("registrazione-page.fxml");
    }
}
