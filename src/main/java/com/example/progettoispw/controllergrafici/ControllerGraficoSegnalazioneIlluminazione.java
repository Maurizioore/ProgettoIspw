package com.example.progettoispw.controllergrafici;

import bean.BeanSegnalazionePaloIlluminazione;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;


public class ControllerGraficoSegnalazioneIlluminazione extends ControllerGraficoLoginPage {

    /*questo controller è associato alla PaginaSegnalaProblemaIlluminazione la quale possiede:
    * i DUE textField in cui l'utente inserisce i campi
    * il pulsante invia che consente d' inviare la segnalazione che l'utente ha richiesto  */
    private BeanSegnalazionePaloIlluminazione beanVerificaDati;
    @FXML
    private TextField textFieldNumeroSeriale;
    @FXML
    private TextField textFieldIndirizzo;
    @FXML
    private JFXButton inviaSegnalazioneButton;
    @FXML
    private Label labelErrore;

    //stringhe che prenderanno input dagli utenti, queste stringhe verranno inviate alla bean che fara'
    //i dovuti getter e setter e poi al controller applicativo che genererà un oggetto palo, la bean fara' i controlli
    //sulla validità del palo e dell'indirizzo.

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //questo avrà i seton mouse click dei suoi pulsanti e alla fine chiama super.initialize() di quelli in comune
        inviaSegnalazioneButton.setOnMouseClicked(event->{
            if (textFieldIndirizzo.getText().equals("") ||  textFieldNumeroSeriale.getText().equals("")) {
                labelErrore.setText("inserire entrambi i campi");
            }else{
                //sara' proprio qui che avverrà l'invio ai bean dei dati che ha inserito l'utente
                beanVerificaDati=new BeanSegnalazionePaloIlluminazione(textFieldNumeroSeriale.getText(),textFieldIndirizzo.getText());
                //faccio i controlli sugli input passati, se viene passato il primo controllo il bean passa gli input al
                //controller applicativo, altrimenti ritorna un errore
                String verificaControlli=beanVerificaDati.Controlli();
                if(verificaControlli!=null){
                    labelErrore.setText(verificaControlli);
                }else{
                    labelErrore.setText("segnalazione avvenuta con successo\ntorna alla home =)");
                    //disabilito i campi per inserire l'indirizzo, il numero seriale e il bottone invia in modo tale
                    //da far inviare solo una segnalazione, se l'utente vuole inviarne un altra deve tornare indietro
                    //tra le view e accedere alla view corrente
                    textFieldIndirizzo.setDisable(true);
                    textFieldNumeroSeriale.setDisable(true);
                    inviaSegnalazioneButton.setDisable(true);
                }
            }
        });
        //questo viene chiamato per implementare le azioni ai button che sono in comune tra le schermate, essendo questa
        //classe figlia di ControllerGraficoLoginPage, questo metodo super chiamerà initialize di ControllerGraficoLoginPage
        //che a sua volta chiamerà initialize di ControllerGraficoSenzaAccesso
        super.initialize(url,resourceBundle);
    }

}
