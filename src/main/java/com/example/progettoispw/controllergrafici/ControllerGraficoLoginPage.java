package com.example.progettoispw.controllergrafici;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerGraficoLoginPage implements Initializable{

    @FXML
    private JFXButton loginButton;

    @FXML
    private Label menu;

    @FXML
    private Label menuBack;

    @FXML
    private AnchorPane slider;
    @FXML
    private JFXButton ritornaHomeButton;
    @FXML
    private JFXButton aiutoButton;
    @FXML
    private JFXButton contattiButton;
    @FXML
    private JFXButton chiSiamoButton;

    private ControllerGraficoSenzaAccesso controllerGraficoSenzaAccesso= new ControllerGraficoSenzaAccesso();
    //aggiunta ora con il singleton
    private ControllerVisulizzatoreScene controllerVisulizzatoreScene=ControllerVisulizzatoreScene.getInstance(null);

    /*questa classe gestisce la login page, avando piu' button e non avendoli in comune con le altre page, ho creato
    * questo controller grafico apposta per essa, questa classe e' il controller grafico della page login-signin-page.fxml,
    * usa anche la classe ControllerGraficoSenzaAccesso nel caso in cui si dovessero premere altri pulsanti , lo faccio
    * per non dover duplicare codice inutilmente*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        /*questo ritornaHomeButton e' una duplicazione di un codice che si torva anche in ControllerGraficoAllSenzaLogin
         * in questo caso ho preferito duplicare codice gia esistente poiche e' un botton in comune alle schermate
         * secondarie ma non alla principale , ovviamente se si dovesse apportare qualche modifica qui bisognera' fare
         * il duale nel controller GraficoGraficoAllSenzaLogin */

        ritornaHomeButton.setOnMouseClicked(event->{
            try {
                controllerVisulizzatoreScene.visualizzaScena("prova-home.fxml");
            }catch(Exception e){
                System.out.println("error: "+ e);
            }
        });
        //gli altri pulsanti del menu invece sono comuni a tutte le page, faccio quindi un riuso di quella logica che
        //ho gia scritto
        aiutoButton.setOnMouseClicked(event->{
            try {
                controllerGraficoSenzaAccesso.aiutoAccess();
            }catch (Exception e){
                System.out.println("error :" + e);
            }
        });
        chiSiamoButton.setOnMouseClicked(event->{
            try {
                controllerGraficoSenzaAccesso.chiSiamoAccess();
            }catch (Exception e){
                System.out.println("error :" + e);
            }
        });
        contattiButton.setOnMouseClicked(event->{
            try {
                controllerGraficoSenzaAccesso.contattiAccess();
            }catch (Exception e){
                System.out.println("error :" + e);
            }
        });
        /*ora devo settare i nomi agli altri pulsanti, devo dare l'accesso i controllerGraficoSenzaAccesso e collegarlo
        * qui come quelli di sopra, ovviamente  accedi  e registrati non devo metterli in ControllerGraficoSenzaAccessi
        * ma devo metterli dentro questa classe come ho fatto per indietroButton, dovro creare quindi un AccediButton e un
        * registratiButton e fare come ho fatto per indietroButton dando cosa deve accadere quando clicco con mouse */
    }
}
