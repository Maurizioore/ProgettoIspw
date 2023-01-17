package com.example.progettoispw.controllergrafici;

import bean.BeanListeElementi;
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //devo chiedere al database tutte le segnalazioni che quell'utente ha effettuato, quindi le chiedo ad un
        //controller applicativo
        //volendo potrei mettere un button chiamato mostraSeganalazioni e mettere questo codice nel
        //mostraSegnalazioni.setOnMouseClicked , almeno se sonar cloud da errore lo risolviamo
        try {
            //questa variabile contatore riporta il numero di segnalazioni ancora attive ell'utente
            BeanListeElementi beanListeElementi=new BeanListeElementi();
            ControllerApplicativoSegnalazioniAttive controllerApplicativoSegnalazioniAttive =new ControllerApplicativoSegnalazioniAttive(beanListeElementi);
            int contatore=beanListeElementi.listaIndirizzi.size();
            //per ogni segnalazione devo creare una label, settare il testo dentro la label stessa
            listViewName.setFixedCellSize(90);
            for(int i=0;i<contatore;i++){
                //aggiungo la label alla view
                Label label=new Label();
                label.setText("numero seriale " + beanListeElementi.restituisciNumeroSeriale(i) +" indirizzo : "+ beanListeElementi.restituisciIndirizzo(i) +" stato: " + beanListeElementi.restituisciStato(i));
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
