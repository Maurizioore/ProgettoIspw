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
    private Label label1;
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
            //passo il bean al controller applicativo che riempira le liste in base alle segnalazioni attive dell'utente
            new ControllerApplicativoSegnalazioniAttive(beanListeElementi);
            //se non e' stata ricevuta nessuna eccezzione vuol dire che non ci sono stati errori e che qualcosa nelle liste
            //dei bean e' stato inserito, creo 2 contatori uno che tiene conto dei pali e uno delle buche cosi so' il numero di
            //segnalazioni che l'utente ha per quelle 2 entita
            //contatore che tiene il numero di indirizzi dei pali ( per come ho costrutito il tutto a n indirizzi corrispondono
            //n numeri di pali, quindi contare gli indirizzi equivale a contare il numero di pali segnalati dall'utente)
            int contatorePali=beanListeElementi.listaIndirizzi.size();
            System.out.println("sono il contatore pali in controller grafico segnalazioni attive, ho trovato "+contatorePali+" pali");
            //discorso duale per il contatore che conta gli indirizzi delle buche
            int contatoreBuche=beanListeElementi.listaIndirizziBucaStradale.size();
            System.out.println("sono il contatore buche in controller grafico segnalazioni attive, ho trovato "+contatoreBuche+" buche");
            //per ogni segnalazione devo creare una label, settare il testo dentro la label stessa
            listViewName.setFixedCellSize(90);
            //se ci sono dei pali li mostro
            if(contatorePali!=0) {
                label1 = new Label();
                label1.setText("PALI SEGNALATI\n");
                listViewName.getItems().add(label1);
                for (int i = 0; i < contatorePali; i++) {
                    //aggiungo la label alla view
                    label1 = new Label();
                    label1.setText(i + 1 + " palo segnalato\nnumero seriale: " + beanListeElementi.restituisciNumeroSeriale(i) + "\nindirizzo: " + beanListeElementi.restituisciIndirizzo(i) + "\nstato: " + beanListeElementi.restituisciStato(i));
                    listViewName.getItems().add(label1);
                }
            }
            if(contatoreBuche!=0) {
                label1 = new Label();
                label1.setText("BUCHE SEGNALATE\n");
                listViewName.getItems().add(label1);
                for (int i = 0; i < contatoreBuche; i++) {
                    //aggiungo la label alla view
                    label1 = new Label();
                    label1.setText(i + 1 + " buca segnalata\n profondità: " + beanListeElementi.restituisciProfonditaBuca(i) + "\nindirizzo: " + beanListeElementi.restituisciIndirizzoBucaStradale(i) + "\nstato: " + beanListeElementi.restituisciStatoBucaStradale(i));
                    listViewName.getItems().add(label1);
                }
            }
        }catch(SQLException | NonEsistonoSegnalazioniException | ErroreLetturaPasswordException e){
            labelErrore.setText(e.getMessage());
        }
        ritornaHomeButton.setOnMouseClicked(event->{
            try {
                controllerVisualizzatoreScene.visualizzaScena("prova-home.fxml");
            }catch(Exception e){
                System.out.println("error: "+ e);
            }
        });
    }
}
