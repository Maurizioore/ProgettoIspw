package com.example.progettoispw.controllergrafici;

import javafx.fxml.FXML;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerGraficoPaginaSegnalaProblema extends ControllerGraficoLoginPage {

    /*questo controller è associato alla pagina dei segnala problemi, anche questa ha i pulsanti in comune
    * nella barra del menu e quindi anche questa riuserà il codice di quei pulsanti che sono stati gia' definiti
    * inoltre implementerà la logica dei suoi button che non ha in comune con le altre view */
    //inizio implementando la logica dei buttonComuni

    //aggiunta ora con il singleton
    private final ControllerVisualizzatoreScene controllerVisualizzatoreScene=ControllerVisualizzatoreScene.getInstance(null);
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //questo avrà i seton mouse click dei suoi pulsanti e alla fine chiama super.initialize di quelli in comune
        super.initialize(url,resourceBundle);
    }
    //inizialmente questo metodo lo avevo messo nell' initialize, purtroppo pero' con questo con i setOnMouseClick nn mi
    //faceva aprire gli altri pulsanti che sono in comune con le schermate uscendo con il codice 255, e quindi ho deciso
    //d'implementarlo come metodo a parte fuori dall' initialize
    @FXML
    public void segnalaPaloAccess() throws Exception{
        controllerVisualizzatoreScene.visualizzaScena("PaginaSegnalaProblemaIlluminazione.fxml");
    }
}