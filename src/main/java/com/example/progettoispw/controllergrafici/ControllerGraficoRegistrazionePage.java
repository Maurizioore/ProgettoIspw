package com.example.progettoispw.controllergrafici;

import bean.BeanRegistrazione;
import com.jfoenix.controls.JFXButton;
import controllerapplicativi.ControllerApplicativoRegistrazioneAlSistema;
import eccezioni.NonEsisteUtenteNelSistemaException;
import eccezioni.UtenteEsistenteException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControllerGraficoRegistrazionePage extends ControllerGraficoGenerale{
    //controller grafico che gestisce la pagina di registrazione al sistema

    private final ControllerVisualizzatoreScene controllerVisualizzatoreScene=ControllerVisualizzatoreScene.getInstance(null);

    @FXML
    private TextField textFieldUsername;
    @FXML
    private PasswordField passwordFieldPassword;
    @FXML
    private TextField textFieldEmail;
    @FXML
    private JFXButton registratiAlSistemaButton;
    @FXML
    private Label labelComunicazione;

    /*questa classe gestisce la login page, avendo piu' button e non avendoli in comune con le altre page, ho creato
     * questo controller grafico apposta per essa, questa classe è il controller grafico della page login-registrazione-page.fxml,
     * usa anche la classe ControllerGraficoSenzaAccesso nel caso in cui si dovessero premere altri pulsanti, lo faccio
     * per non dover duplicare codice inutilmente*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //questo avrà i suoi pulsanti e alla fine chiamerà il super di quelli in comune, per non darli al figlio
        //quelli che non sono in comune dovrei farli private
        registratiAlSistemaButton.setOnMouseClicked(event->{
            //prima controllo che i campi siano stati tutti compilati
            if(textFieldUsername.getText().equals("")||textFieldEmail.getText().equals("")||passwordFieldPassword.getText().equals("")){
                labelComunicazione.setText("inserire tutti i campi");
            }else{
                //tutti i campi sono stati passati, creo un bean che prendi questi parametri in input
                BeanRegistrazione beanRegistrazione=new BeanRegistrazione(textFieldEmail.getText(),passwordFieldPassword.getText(),textFieldUsername.getText());
                // faccio fare al bean i controlli sintattici
                String controlliSintatticiEmail=beanRegistrazione.svolgiControlli();
                if (controlliSintatticiEmail == null) {
                    //la sintassi dell'email e' corretta, invio il bean al controller applicativo che gestisce la registrazione
                    try{
                        ControllerApplicativoRegistrazioneAlSistema controllerApplicativoRegistrazioneAlSistema=new ControllerApplicativoRegistrazioneAlSistema(beanRegistrazione);
                        textFieldEmail.setDisable(true);
                        textFieldUsername.setDisable(true);
                        passwordFieldPassword.setDisable(true);
                        registratiAlSistemaButton.setDisable(true);
                        labelComunicazione.setText("registrazione avvenuta con successo");
                    }catch (SQLException| UtenteEsistenteException e){
                        labelComunicazione.setText(e.getMessage());
                    }
                }else{
                    labelComunicazione.setText(controlliSintatticiEmail);
                }
            }
        });

        super.initialize(url,resourceBundle);
    }

}
