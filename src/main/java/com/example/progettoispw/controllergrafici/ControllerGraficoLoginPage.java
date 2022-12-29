package com.example.progettoispw.controllergrafici;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerGraficoLoginPage extends ControllerGraficoSenzaAccesso {
    @FXML
    private JFXButton ritornaHomeButton;

    //aggiunta ora con il singleton
    private final ControllerVisualizzatoreScene controllerVisualizzatoreScene=ControllerVisualizzatoreScene.getInstance(null);

    /*questa classe gestisce la login page, avendo piu' button e non avendoli in comune con le altre page, ho creato
    * questo controller grafico apposta per essa, questa classe è il controller grafico della page login-registrazione-page.fxml,
    * usa anche la classe ControllerGraficoSenzaAccesso nel caso in cui si dovessero premere altri pulsanti, lo faccio
    * per non dover duplicare codice inutilmente*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*questo ritornaHomeButton è una duplicazione di un codice che si torva anche in ControllerGraficoAllSenzaLogin
         * in questo caso ho preferito duplicare codice gia esistente poiché è un button in comune alle schermate
         * secondarie ma non alla principale, ovviamente se si dovesse apportare qualche modifica qui bisognerà fare
         * il duale nel controller GraficoGraficoAllSenzaLogin */
        ritornaHomeButton.setOnMouseClicked(event->{
            try {
                controllerVisualizzatoreScene.visualizzaScena("prova-home.fxml");
            }catch(Exception e){
                System.out.println("error: "+ e);
            }
        });
        super.initialize(url,resourceBundle);
        /*ora devo settare i nomi agli altri pulsanti, devo dare l'accesso i controllerGraficoSenzaAccesso e collegarlo
        * qui come quelli di sopra, ovviamente accedi e registrati non devo metterli in ControllerGraficoSenzaAccessi
        * ma devo metterli dentro questa classe come ho fatto per indietroButton, dovrò creare quindi un AccediButton e un
        * registratiButton e fare come ho fatto per indietroButton dando cosa deve accadere quando clicco con mouse */
    }
}
