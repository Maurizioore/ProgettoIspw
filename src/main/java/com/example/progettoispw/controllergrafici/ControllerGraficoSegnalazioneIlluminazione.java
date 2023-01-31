package com.example.progettoispw.controllergrafici;

import bean.BeanSegnalaEntita;
import com.jfoenix.controls.JFXButton;
import controllerapplicativi.ControllerApplicativoSegnalazioneEntita;
import factory.TypeOfPersistence;
import eccezioni.*;
import factory.TypeEntita;
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
    private BeanSegnalaEntita beanVerificaDati;
    @FXML
    private JFXButton inviaSegnalazioneInLocaleButton;
    @FXML
    private TextField textFieldNumeroSeriale;
    @FXML
    private TextField textFieldIndirizzo;
    @FXML
    private JFXButton inviaSegnalazioneButton;
    @FXML
    private Label labelErrore;
    //se sono in questo controller grafico vuol dire che l'utente sta segnalando un palo dell'illuminazione, quindi la
    //mia entita stradale sara' di tipo type_palo_illuminazione
    private TypeEntita typeEntita = TypeEntita.PALOILLUMINAZIONE;
    private TypeOfPersistence typeOfPersistence;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //questo avrà i seton mouse click dei suoi pulsanti e alla fine chiama super.initialize() di quelli in comune
        inviaSegnalazioneButton.setOnMouseClicked(event -> {
            if (controllaInput()) {
                //sara' proprio qui che avverrà l'invio al bean dei dati che ha inserito l'utente in input
                try {
                    typeOfPersistence = TypeOfPersistence.JDBC;
                    beanVerificaDati=beanVerifica(textFieldNumeroSeriale.getText(), textFieldIndirizzo.getText(), typeEntita, typeOfPersistence);
                    //la lunghezza seriale del palo inserita dall'utente è corretta, invio il bean al controller applicativo
                    ControllerApplicativoSegnalazioneEntita controllerApplicativoSegnalazioneEntita = new ControllerApplicativoSegnalazioneEntita(beanVerificaDati);
                    //se non c'e' stata nessuna eccezione vuol dire che la segnalazione e' avvenuta con successo
                    //lo comunico all'utente e blocco i pulsanti per non far inviare la stessa segnalazione
                    //in caso dovesse premere per sbaglio di nuovo il pulsante invia
                    labelErrore.setText("segnalazione avvenuta con successo\ntorna alla home =)");
                    disattivaButton();
                } catch (LunghezzaInputException | SQLException | ErroreLetturaPasswordException |
                         SegnalazioneGiaAvvenutaException | NessunAccessoEffettuatoException |
                          TipoEntitaException e) {
                    verificaEccezione(e);
                }
            }
        });
        // codice che si attiva se l'utente clicca il button per salvare il problema segnalato in locale e non su un database
        inviaSegnalazioneInLocaleButton.setOnMouseClicked(event -> {
            //controlla input torna false se i campi sono stati inseriti
            if (controllaInput()) {
                try {
                    //il tipo di persistenza sarà il file system ora
                    typeOfPersistence = TypeOfPersistence.FILESYSTEM;
                    beanVerificaDati = beanVerifica(textFieldNumeroSeriale.getText(), textFieldIndirizzo.getText(), typeEntita, typeOfPersistence);
                    ControllerApplicativoSegnalazioneEntita controllerApplicativoSegnalazioneEntita = new ControllerApplicativoSegnalazioneEntita(beanVerificaDati);
                    //se non c'e' stata nessuna eccezione vuol dire che la segnalazione e' avvenuta con successo
                    //lo comunico all'utente e blocco i pulsanti per non far inviare la stessa segnalazione
                    //in caso dovesse premere per sbaglio di nuovo il pulsante invia
                    labelErrore.setText("segnalazione avvenuta con successo\ntorna alla home =)");
                    disattivaButton();
                } catch (LunghezzaInputException | SQLException | ErroreLetturaPasswordException |
                         SegnalazioneGiaAvvenutaException | NessunAccessoEffettuatoException | TipoEntitaException e) {
                    verificaEccezione(e);
                    labelErrore.setText(e.getMessage());
                }
            }
        });
        //questo viene chiamato per implementare le azioni ai button che sono in comune tra le schermate, essendo questa
        //classe figlia di ControllerGraficoLoginPage, questo metodo super chiamerà initialize di ControllerGraficoLoginPage
        //che a sua volta chiamerà initialize di ControllerGraficoSenzaAccesso
        super.initialize(url, resourceBundle);
    }

    public BeanSegnalaEntita beanVerifica(String numeroSeriale, String indirizzo, TypeEntita te, TypeOfPersistence top) throws LunghezzaInputException {
        BeanSegnalaEntita beanSegnalaEntita = new BeanSegnalaEntita(numeroSeriale, indirizzo, te, top);
        beanSegnalaEntita.controllaInputPalo();
        return beanSegnalaEntita;
    }

    public void disattivaButton() {
        textFieldIndirizzo.setDisable(true);
        textFieldNumeroSeriale.setDisable(true);
        inviaSegnalazioneButton.setDisable(true);
        inviaSegnalazioneInLocaleButton.setDisable(true);
    }

    public boolean controllaInput() {
        if (textFieldIndirizzo.getText().equals("") || textFieldNumeroSeriale.getText().equals("")) {
            labelErrore.setText("inserire entrambi i campi");
            return false;
        }
        return true;
    }
    public void verificaEccezione(Exception e){
        labelErrore.setText(e.getMessage());
        if (e.getClass() == SegnalazioneGiaAvvenutaException.class) {
            /*se l'eccezione è di tipo segnalazione già avvenuta l'utente ha portato a termine quello che
             * voleva fare quindi posso disabilitare i pulsanti */
            labelErrore.setText(e.getMessage());
            disattivaButton();
        }
    }
}
