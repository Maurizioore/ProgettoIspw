package com.example.progettoispw.controllergrafici;

import bean.BeanSegnalaEntita;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import controllerapplicativi.ControllerApplicativoSegnalazioneEntita;
import eccezioni.*;
import factory.TypeEntita;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
    private JFXButton inviaSegnalazioneButton;
    @FXML
    private Label labelErrore;
    //se sono in questo controller grafico vuol dire che l'utente sta segnalando una buca , quindi la
    //mia entita stradale sara' di tipo type_buca_stradale
    private TypeEntita typeEntita=TypeEntita.type_buca_stradale;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        inviaSegnalazioneButton.setOnMouseClicked(event-> {
            if (textFieldIndirizzo.getText().equals("") || sliderProfonditaCmBuca.getValue()==0) {
                labelErrore.setText("inserire entrambi i campi");
            } else {
                try {
                    beanVerificaDati = new BeanSegnalaEntita( Integer.toString((int)sliderProfonditaCmBuca.getValue()),textFieldIndirizzo.getText(), typeEntita);
                    //questi dati devono essere mandati al controller applicativo
                    ControllerApplicativoSegnalazioneEntita controllerApplicativoSegnalazioneEntita=new ControllerApplicativoSegnalazioneEntita(beanVerificaDati);
                    labelErrore.setText("segnalazione avvenuta con successo\ntorna alla home =)");
                    textFieldIndirizzo.setDisable(true);
                    sliderProfonditaCmBuca.setDisable(true);
                    inviaSegnalazioneButton.setDisable(true);
                } catch( SQLException | ErroreLetturaPasswordException | SegnalazioneGiaAvvenutaException | NessunAccessoEffettuatoException | TipoEntitaException e){
                    labelErrore.setText(e.getMessage());
                }
            }
        });
        super.initialize(url,resourceBundle);
    }
}
