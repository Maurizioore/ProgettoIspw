package com.example.progettoispw.controllergrafici;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import utilityaccesso.UtilityAccesso;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerGraficoLogoutPage extends ControllerGraficoGenerale{

    @FXML
    private Label labelUtente;
    @FXML
    private JFXButton logoutButton;
    private final ControllerVisualizzatoreScene controllerVisualizzatoreScene=ControllerVisualizzatoreScene.getInstance(null);
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    labelUtente.setText(UtilityAccesso.nomeUtenteNelDatabase);
    logoutButton.setOnMouseClicked(event->{
        //porto a null i valori globali di utilityAccesso
        UtilityAccesso.codiceUtente=null;
        UtilityAccesso.nomeUtenteNelDatabase=null;
        //poi carico la pagina del login
        try {
            controllerVisualizzatoreScene.visualizzaScena("login-registrazione-page.fxml");
        } catch (Exception e) {
            System.exit(-1);
        }
    });
        super.initialize(url,resourceBundle);
    }

}
