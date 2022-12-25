package com.example.progettoispw.controllerGrafici;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerGraficoPaginaSegnalaProblema implements Initializable {

    /*questo controller e' associato alla pagina dei segnala problemi, anche questa ha i pulsanti in comune
    * nella barra del menu e quindi anche questa riusera' il codice di quei pulsanti che sono stati gia' definiti
    * inoltre implementera' la logica dei suoi button che non ha in comune con le altre view */
    //inizio implementando la logica dei buttonComuni
    @FXML
    private JFXButton loginButton;

    @FXML
    private JFXButton ritornaHomeButton;
    @FXML
    private JFXButton aiutoButton;
    @FXML
    private JFXButton contattiButton;
    @FXML
    private JFXButton chiSiamoButton;

    private ControllerGraficoSenzaAccesso controllerGraficoSenzaAccesso= new ControllerGraficoSenzaAccesso();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ritornaHomeButton.setOnMouseClicked(event->{
            try {
                new ControllerVisulizzatoreScene("prova-home.fxml");
            }catch(Exception e){
                System.out.println("error: "+ e);
            }
        });
        loginButton.setOnMouseClicked(event->{
            try{
                controllerGraficoSenzaAccesso.loginAccess();
            }catch (Exception e){
                System.out.println("error : "+e);
            }
        });
        chiSiamoButton.setOnMouseClicked(event->{
            try{
                controllerGraficoSenzaAccesso.chiSiamoAccess();
            }catch (Exception e){
                System.out.println("error : "+e);
            }
        });
        aiutoButton.setOnMouseClicked(event->{
            try{
                controllerGraficoSenzaAccesso.aiutoAccess();
            }catch (Exception e){
                System.out.println("error : "+e);
            }
        });
        contattiButton.setOnMouseClicked(event->{
            try{
                controllerGraficoSenzaAccesso.contattiAccess();
            }catch (Exception e){
                System.out.println("error : "+e);
            }
        });
    }

}
