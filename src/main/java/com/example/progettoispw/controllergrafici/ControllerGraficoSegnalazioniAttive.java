package com.example.progettoispw.controllergrafici;

import com.jfoenix.controls.JFXButton;
import controllerapplicativi.ControllerApplicativoSegnalazioniAttive;
import eccezioni.ErroreLetturaPasswordException;
import eccezioni.NonEsistonoSegnalazioniException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerGraficoSegnalazioniAttive implements Initializable {
    /*fai check prima di entrare qua se l'utente e' loggato poi per ongni segnalazione aperta prendi
    * e aggiungi un bottone nella list view */
   @FXML
   private ListView<Label> listViewName;
   @FXML
   private JFXButton ritornaHomeButton;
    @FXML
    private Label labelErrore ;
   ControllerVisualizzatoreScene controllerVisualizzatoreScene=ControllerVisualizzatoreScene.getInstance(null);
   private List<List<String>> segnalazioniEffettuateDallUtente;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //devo chiedere al database tutte le segnalazioni che quell'utente ha effettuato, quindi le chiedo ad un
        //controller applicativo
        //volendo potrei mettere un button chiamato mostraSeganalazioni e mettere questo codice nel
        //mostraSegnalazioni.setOnMouseClicked , almeno se sonar cloud da errore lo risolviamo
        try {
            ControllerApplicativoSegnalazioniAttive controllerApplicativoSegnalazioniAttive = new ControllerApplicativoSegnalazioniAttive();
            //chiedo al controller applicativo di darmi le segnalazioni che l'utente ha effettuato
            segnalazioniEffettuateDallUtente = controllerApplicativoSegnalazioniAttive.ritornaListaSegnalazioni();
            //questa e' una lista che contiene al suo interno 3 liste di stringhe
            //calcolo intanto la lunghezza della 2 lista
            List<String> paliSegnalati=segnalazioniEffettuateDallUtente.get(0);
            List<String> indirizziAssociatiAiPali=segnalazioniEffettuateDallUtente.get(1);
            List<String> statoDellaSegnalazioneDeiPali=segnalazioniEffettuateDallUtente.get(2);
            //questa variabile contatore riporta il numero di segnalazioni ancora attive ell'utente
            int contatore=paliSegnalati.size();
            //per ogni segnalazione devo creare una label, settare il testo dentro la label stessa
            listViewName.setFixedCellSize(90);
            for(int i=0;i<contatore;i++){
                //aggiungo la label alla view
                Label label=new Label();
                label.setText("numero seriale: " +paliSegnalati.get(i)+ " indirizzo:  " +indirizziAssociatiAiPali.get(i)+" stato segnalazione: " +statoDellaSegnalazioneDeiPali.get(i));
                listViewName.getItems().add(label);
            }
        }catch(SQLException | NonEsistonoSegnalazioniException | ErroreLetturaPasswordException e){
            labelErrore.setText(e.getMessage());
        }

        //devo contare le segnalazioni che ci sono nella lista e poi con un for creo dei button
        //
        ritornaHomeButton.setOnMouseClicked(event->{
            try {
                controllerVisualizzatoreScene.visualizzaScena("prova-home.fxml");
            }catch(Exception e){
                System.out.println("error: "+ e);
            }
        });
    }
}
