package com.example.progettoispw.controllergrafici;

import bean.BeanSegnalazionePaloIlluminazione;
import com.jfoenix.controls.JFXButton;
import controllerapplicativi.ControllerApplicativoSegnalazionePaloIlluminazione;
import eccezioni.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class ControllerGraficoSegnalazioneIlluminazione extends ControllerGraficoGenerale {

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
                //sara' proprio qui che avverrà l'invio al bean dei dati che ha inserito l'utente in input
                try {
                    beanVerificaDati = new BeanSegnalazionePaloIlluminazione(textFieldNumeroSeriale.getText(), textFieldIndirizzo.getText());
                    beanVerificaDati.controllaInputPalo();
                    //la lunghezza seriale del palo inserita dall'utente è corretta, invio il bean al controller applicativo
                    ControllerApplicativoSegnalazionePaloIlluminazione controllerApplicativoSegnalazionePaloIlluminazione=new ControllerApplicativoSegnalazionePaloIlluminazione(beanVerificaDati);
                    //se non c'e' stata nessuna eccezione vuol dire che la segnalazione e' avvenuta con successo
                    //lo comunico all'utente e blocco i pulsanti per non far inviare la stessa segnalazione
                    //in caso dovesse premere per sbaglio di nuovo il pulsante invia
                    labelErrore.setText("segnalazione avvenuta con successo\ntorna alla home =)");
                    textFieldIndirizzo.setDisable(true);
                    textFieldNumeroSeriale.setDisable(true);
                    inviaSegnalazioneButton.setDisable(true);
                }catch (LunghezzaInputException | NonEsisteIndirizzoException | NonEsisteNumeroSerialeException |
                        SQLException | SegnalazioneGiaAvvenutaException | ErroreLetturaPasswordException e){
                    labelErrore.setText(e.getMessage());
                    if(e.getClass()==SegnalazioneGiaAvvenutaException.class){
                        /*se l'eccezione è di tipo segnalazione già avvenuta l'utente ha portato a termine quello che
                        * voleva fare quindi posso disabilitare i pulsanti */
                        labelErrore.setText(e.getMessage());
                        textFieldIndirizzo.setDisable(true);
                        textFieldNumeroSeriale.setDisable(true);
                        inviaSegnalazioneButton.setDisable(true);
                    }
                }
            }
        });
        //questo viene chiamato per implementare le azioni ai button che sono in comune tra le schermate, essendo questa
        //classe figlia di ControllerGraficoLoginPage, questo metodo super chiamerà initialize di ControllerGraficoLoginPage
        //che a sua volta chiamerà initialize di ControllerGraficoSenzaAccesso
        super.initialize(url,resourceBundle);
    }

}
