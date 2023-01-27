package com.example.progettoispw.controllergrafici;

import javafx.fxml.FXML;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerGraficoPaginaSegnalaProblema extends ControllerGraficoGenerale {

    /*questo controller è associato alla pagina dei segnala problemi, anche questa ha i pulsanti in comune
    * nella barra del menu e quindi anche questa riuserà il codice di quei pulsanti che sono stati gia' definiti
    * inoltre implementerà la logica dei suoi button che non ha in comune con le altre view */
    //inizio implementando la logica dei buttonComuni
    private final ControllerVisualizzatoreScene controllerVisualizzatoreScene=ControllerVisualizzatoreScene.getInstance(null);

    //inizialmente questo metodo lo avevo messo nell' initialize, purtroppo pero' con questo con i setOnMouseClick nn mi
    //faceva aprire gli altri pulsanti che sono in comune con le schermate uscendo con il codice 255, e quindi ho deciso
    //d'implementarlo come metodo a parte fuori dall' initialize

    //a questa classe non serve il l'override di initialize, perche' effetivamente quello che dovrebbe fare in quel caso
    //in cui fosse presente e' chiamare super.intialize e basta poiche non ha sui bottoni a parte che condivide con altre
    //classi e che mi fanno duplicare il codice, tanto vale allora non mettere proprio intialize e far gestire il click c
    //come ho fatto per quello sotto
    @FXML
    public void segnalaPaloAccess() throws Exception{
        controllerVisualizzatoreScene.visualizzaScena("PaginaSegnalaProblemaIlluminazione.fxml");
    }
    @FXML
    public void segnalaBucaAccess() throws Exception{
        controllerVisualizzatoreScene.visualizzaScena("PaginaSegnalaProblemaBucaStradale.fxml");
    }
}