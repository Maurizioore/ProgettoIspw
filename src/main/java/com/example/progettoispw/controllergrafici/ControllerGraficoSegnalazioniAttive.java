package com.example.progettoispw.controllergrafici;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerGraficoSegnalazioniAttive implements Initializable {
    /*fai check prima di entrare qua se l'utente e' loggato poi per ongni segnalazione aperta prendi
    * e aggiungi un bottone nella list view */
   @FXML
   private ListView<JFXButton> listViewName;
   @FXML
   private JFXButton ritornaHomeButton;
   ControllerVisualizzatoreScene controllerVisualizzatoreScene=ControllerVisualizzatoreScene.getInstance(null);


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listViewName.setFixedCellSize(90);
        for(int i=1;i<15;i++){
            JFXButton button=new JFXButton();
            button.setText(Integer.toString(i));
            button.setMinHeight(90);
            button.setMinWidth(659);
            listViewName.getItems().add(button);
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
