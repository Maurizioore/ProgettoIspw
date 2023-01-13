package com.example.progettoispw.controllergrafici;

import com.jfoenix.controls.JFXButton;
import controllerapplicativi.ControllerApplicativoSegnalazioniRisolte;
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
public class ControllerGraficoSegnalazioniRisolte implements Initializable{
    @FXML
    private JFXButton ritornaHomeButton;
    @FXML
    private ListView<Label> listViewRisolteName;
    @FXML
    private Label labelErrore;
    ControllerVisualizzatoreScene controllerVisualizzatoreScene=ControllerVisualizzatoreScene.getInstance(null);

    private List<List<String>> segnalazioniRisolte;
    //prende tutto dal padre
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        try{

            //se l'utente e' entrato nella schermata vuol dire che possiede un account, gli mostro le sue
            //segnalazioni che sono state risolte
            //chiamo il controller applicativo che si preoccupa di restituire tutto cio che l'utente ha segnalato e che e' stato risolto
            ControllerApplicativoSegnalazioniRisolte controllerApplicativoSegnalazioniRisolte= new ControllerApplicativoSegnalazioniRisolte();
            //ritorno tutte le segnalazioni risolte
            segnalazioniRisolte=controllerApplicativoSegnalazioniRisolte.ritornaListaSegnalazioni();
            //separo le 2 liste che sono state messe in segnalazioni risolte
            List<String> paliRiparati=segnalazioniRisolte.get(0);
            List<String> indirizzi=segnalazioniRisolte.get(1);
            //verifico la grandezza di una di queste liste ( che sarà uguale all'altra) per capire quamte righe il controller grafico
            //deve creare
            int contatore=paliRiparati.size();
            listViewRisolteName.setFixedCellSize(90);
            //creo le righe che mostrano le segnalazioni
            for(int i=0;i<contatore;i++){
                //aggiungo la label alla view
                Label label=new Label();
                label.setText("numero seriale: " +paliRiparati.get(i)+ " indirizzo:  " +indirizzi.get(i));
                listViewRisolteName.getItems().add(label);
            }
        } catch (SQLException | NonEsistonoSegnalazioniException | ErroreLetturaPasswordException e) {
            labelErrore.setText(e.getMessage());
        }
        //se l'utente clicca sul pulsante che ritorna alla home viene attivato questo metodo
        ritornaHomeButton.setOnMouseClicked(event->{
            try {
                controllerVisualizzatoreScene.visualizzaScena("prova-home.fxml");
            }catch(Exception e){
                System.out.println("error: "+ e);
            }
        });
    }
}
