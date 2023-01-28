package com.example.progettoispw.controllergrafici;

import bean.BeanSegnalaEntita;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import controllerapplicativi.ControllerApplicativoSegnalazioneEntita;
import factory.TypeOfPersistence;
import eccezioni.*;
import factory.TypeEntita;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControllerGraficoSegnalazioneBucaStradale extends ControllerGraficoGenerale{
    private BeanSegnalaEntita beanVerificaDati;
    @FXML
    private JFXSlider sliderProfonditaCmBuca;
    @FXML
    private TextField textFieldIndirizzo;
    @FXML
    private JFXButton inviaSegnalazioneButton1;
    @FXML
    private Label labelErrore;
    @FXML
    private JFXButton inviaSegnalazioneButtonInLocale;
    //se sono in questo controller grafico vuol dire che l'utente sta segnalando una buca , quindi la
    //mia entita stradale sara' di tipo type_buca_stradale
    private TypeEntita typeEntita=TypeEntita.type_buca_stradale;
    private TypeOfPersistence typeOfPersistence;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        inviaSegnalazioneButton1.setOnMouseClicked(event-> {
            if (textFieldIndirizzo.getText().equals("") || sliderProfonditaCmBuca.getValue()==0) {
                labelErrore.setText("inserire entrambi i campi");
            } else {
                try {
                    typeOfPersistence=TypeOfPersistence.JDBC;
                    beanVerificaDati=beanVerifica(Integer.toString((int)sliderProfonditaCmBuca.getValue()),textFieldIndirizzo.getText(), typeEntita,typeOfPersistence);
                    //questi dati devono essere mandati al controller applicativo
                    ControllerApplicativoSegnalazioneEntita controllerApplicativoSegnalazioneEntita=new ControllerApplicativoSegnalazioneEntita(beanVerificaDati);
                    labelErrore.setText("segnalazione avvenuta con successo\ntorna alla home =)");
                    disattivaButton();
                } catch(SQLException | IOException| ErroreLetturaPasswordException | SegnalazioneGiaAvvenutaException | NessunAccessoEffettuatoException | TipoEntitaException e){
                    labelErrore.setText(e.getMessage());
                }
            }
        });
        inviaSegnalazioneButtonInLocale.setOnMouseClicked(event->{
            if (textFieldIndirizzo.getText().equals("") || sliderProfonditaCmBuca.getValue()==0) {
                labelErrore.setText("inserire entrambi i campi");
            } else {
                try {
                    typeOfPersistence=TypeOfPersistence.FILESYSTEM;
                    beanVerificaDati=beanVerifica(Integer.toString((int)sliderProfonditaCmBuca.getValue()),textFieldIndirizzo.getText(), typeEntita,typeOfPersistence);
                    //questi dati devono essere mandati al controller applicativo
                    ControllerApplicativoSegnalazioneEntita controllerApplicativoSegnalazioneEntita=new ControllerApplicativoSegnalazioneEntita(beanVerificaDati);
                    labelErrore.setText("segnalazione avvenuta con successo\ntorna alla home =)");
                    disattivaButton();
                } catch(SQLException | IOException| ErroreLetturaPasswordException | SegnalazioneGiaAvvenutaException | NessunAccessoEffettuatoException | TipoEntitaException e){
                    labelErrore.setText(e.getMessage());
                }
            }
        });
        super.initialize(url,resourceBundle);
    }
    public BeanSegnalaEntita beanVerifica(String profondita,String indirizzo,TypeEntita te,TypeOfPersistence top){
        return new BeanSegnalaEntita(profondita,indirizzo, te,top);
    }
    public void disattivaButton(){
        textFieldIndirizzo.setDisable(true);
        sliderProfonditaCmBuca.setDisable(true);
        inviaSegnalazioneButton1.setDisable(true);
        inviaSegnalazioneButtonInLocale.setDisable(true);
    }
}
