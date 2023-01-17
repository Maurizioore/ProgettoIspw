package com.example.progettoispw.controllergrafici;

import bean.BeanListeElementi;
import com.jfoenix.controls.JFXButton;
import controllerapplicativi.ControllerApplicativoSegnalazioniRisolte1;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        try{
            //se l'utente e' entrato nella schermata vuol dire che possiede un account, gli mostro le sue
            //segnalazioni che sono state risolte
            //chiamo il controller applicativo che si preoccupa di restituire tutto cio che l'utente ha segnalato e che e' stato risolto
            BeanListeElementi beanListeElementi=new BeanListeElementi();
            ControllerApplicativoSegnalazioniRisolte1 controllerApplicativoSegnalazioniRisolte1=new ControllerApplicativoSegnalazioniRisolte1(beanListeElementi);
            //in questo punto tutte le segnalazioni risolte sono state aggiunte nella lista dentro il bean, le riprendo allora e le mostro in output
            int contatore=beanListeElementi.listaIndirizzi.size();
            listViewRisolteName.setFixedCellSize(90);
            //creo le righe che mostrano le segnalazioni
            for(int i=0;i<contatore;i++){
                //aggiungo la label alla view
                Label label=new Label();
                label.setText("numero seriale: " +beanListeElementi.restituisciNumeroSeriale(i)+ " indirizzo:  " +beanListeElementi.restituisciIndirizzo(i));
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
