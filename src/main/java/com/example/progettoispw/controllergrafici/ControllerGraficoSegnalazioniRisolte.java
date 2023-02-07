package com.example.progettoispw.controllergrafici;

import bean.BeanListeElementi;
import com.jfoenix.controls.JFXButton;
import controllerapplicativi.ControllerApplicativoTipoSegnalazione;
import eccezioni.ErroreLetturaPasswordException;
import eccezioni.NonEsistonoSegnalazioniException;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
public class ControllerGraficoSegnalazioniRisolte implements Initializable{
    @FXML
    private JFXButton ritornaHomeButton;
    @FXML
    private ListView<Label> listViewRisolteName;
    @FXML
    private Label labelErrore;
    ControllerVisualizzatoreScene controllerVisualizzatoreScene=ControllerVisualizzatoreScene.getInstance(null);
    //se sono in questo controller grafico vuol dire che sono interessato a ricevere le segnalazioni risolte

    private static final TypeOfSegnalazione type_of_segnalazione= TypeOfSegnalazione.RISOLTE;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            //se l'utente e' entrato nella schermata vuol dire che possiede un account, gli mostro le sue
            //segnalazioni che sono state risolte
            //chiamo il controller applicativo che si preoccupa di restituire tutto cio che l'utente ha segnalato e che e' stato risolto
            BeanListeElementi beanListeElementi=new BeanListeElementi(type_of_segnalazione);
            new ControllerApplicativoTipoSegnalazione(beanListeElementi);
            //in questo punto tutte le segnalazioni risolte sono state aggiunte nella lista dentro il bean, le riprendo allora e le mostro in output
            int contatorePali=beanListeElementi.listaIndirizzi.size();
            int contatoreBuche=beanListeElementi.listaIndirizziBucaStradale.size();
            listViewRisolteName.setFixedCellSize(90);
            //se ci sono dei pali li mostro
            if(contatorePali!=0) {
                Label label1 = new Label();
                label1.setText("PALI SEGNALATI\n");
                listViewRisolteName.getItems().add(label1);
                for (int i = 0; i < contatorePali; i++) {
                    //aggiungo la label alla view
                    label1 = new Label();
                    label1.setText(i + 1 + " palo segnalato\nnumero seriale: " + beanListeElementi.restituisciNumeroSeriale(i) + "\nindirizzo: " + beanListeElementi.restituisciIndirizzo(i) + "\nstato: " + beanListeElementi.restituisciStato(i));
                    listViewRisolteName.getItems().add(label1);
                }
            }
            if(contatoreBuche!=0) {
                Label label1 = new Label();
                label1.setText("BUCHE SEGNALATE\n");
                listViewRisolteName.getItems().add(label1);
                for (int i = 0; i < contatoreBuche; i++) {
                    //aggiungo la label alla view
                    label1 = new Label();
                    label1.setText(i + 1 + " buca segnalata\n profondità: " + beanListeElementi.restituisciProfonditaBuca(i) + "\nindirizzo: " + beanListeElementi.restituisciIndirizzoBucaStradale(i) + "\nstato: " + beanListeElementi.restituisciStatoBucaStradale(i));
                    listViewRisolteName.getItems().add(label1);
                }
            }
        } catch (SQLException | NonEsistonoSegnalazioniException | ErroreLetturaPasswordException e) {
            labelErrore.setText(e.getMessage());
        }
        //se l'utente clicca sul pulsante che ritorna alla home viene attivato questo metodo
        ritornaHomeButton.setOnMouseClicked(event->{
            try {
                controllerVisualizzatoreScene.visualizzaScena("prova-home.fxml");
            }catch(Exception e){
                System.exit(-1);
            }
        });
    }
}
