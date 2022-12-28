package com.example.progettoispw.controllergrafici;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;
public class ControllerGraficoSenzaAccesso implements Initializable {

    @FXML
    private Label menu;

    @FXML
    private Label menuBack;

    @FXML
    private AnchorPane slider;

    //aggiunta ora con il singleton
    private ControllerVisulizzatoreScene controllerVisulizzatoreScene=ControllerVisulizzatoreScene.getInstance(null);

    /*questa classe la uso per implementare la logica dei button comuni a tutte le schermate , in particolare questa
    * classe svolge il ruolo di controller grafico per la prova-home.fxml, la quale e' la prima schermata che viene
    * quando start l'app*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        slider.setTranslateX(0);
        menu.setOnMouseClicked(event-> {
            TranslateTransition slide= new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);
            slide.setToX(0);
            slide.play();

            slider.setTranslateX(-176);
            slide.setOnFinished((ActionEvent e )->{
                menu.setVisible(false);
                menuBack.setVisible(true);
            });
        });
        menuBack.setOnMouseClicked(event-> {
            TranslateTransition slide= new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);
            slide.setToX(-176);
            slide.play();

            slider.setTranslateX(0);
            slide.setOnFinished((ActionEvent e )->{
                menu.setVisible(true);
                menuBack.setVisible(false);
            });
        });
    }
    @FXML
    void loginAccess() throws Exception{
        controllerVisulizzatoreScene.visualizzaScena("login-signin-page.fxml");
    }
    @FXML
    void contattiAccess() throws Exception{
        controllerVisulizzatoreScene.visualizzaScena("ContattaciPage.fxml");
    }
    @FXML
    void aiutoAccess() throws Exception {
        controllerVisulizzatoreScene.visualizzaScena("aiutoAccess.fxml");
    }

    @FXML
    void chiSiamoAccess() throws Exception {

        controllerVisulizzatoreScene.visualizzaScena("chiSiamoNoi.fxml");
    }
    @FXML
    void segnalaProblemaAccess() throws Exception {
        controllerVisulizzatoreScene.visualizzaScena("PaginaSegnalaProblema.fxml");
    }

}