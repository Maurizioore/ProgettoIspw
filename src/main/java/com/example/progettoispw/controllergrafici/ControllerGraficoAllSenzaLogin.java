package com.example.progettoispw.controllergrafici;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;


public class ControllerGraficoAllSenzaLogin implements Initializable {

    @FXML
    private JFXButton loginButton;

    @FXML
    private Label menu;

    @FXML
    private Label menuBack;

    @FXML
    private AnchorPane slider;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private JFXButton aiutoButton= new JFXButton();

    @FXML
    private JFXButton chiSiamoButton= new JFXButton();
    @FXML
    private JFXButton contattiButton= new JFXButton();

    @FXML
    private JFXButton ritornaHomeButton=new JFXButton();

    private  ControllerGraficoSenzaAccesso controllerGraficoSenzaAccesso=  new ControllerGraficoSenzaAccesso();
    /*questa classe e' associata alle page aiutoAccess chiSiamoNoi ContattaciPage, cosi come la login page che
    * ha pulsanti non in comune con nessuna schermata, gestisce i pulsanti che ha invece in comune grazie a
    * un istanza di controllerGraficoSenzaAccesso, questa gestisce invece tutti i pulsanti che sono in comune usando
    * anche essa ovviamente un istanza di controllerGraficoSenzaAcceso  */


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*questo ritornaHomeButton e' una duplicazione di un codice che si torva anche in ControllerGraficoLoginPage
        * in questo caso ho preferito duplicare codice gia esistente poiche e' un botton in comune alle schermate
        * secondarie ma non alla principale , ovviamente se si dovesse apportare qualche modifica qui bisognera' fare
        * il duale nel ControllerGraficoLoginPage */

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
