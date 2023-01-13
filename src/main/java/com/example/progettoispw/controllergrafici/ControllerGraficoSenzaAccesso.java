package com.example.progettoispw.controllergrafici;
import com.jfoenix.controls.JFXButton;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import utilityaccesso.UtilityAccesso;

import java.net.URL;
import java.util.ResourceBundle;
public class ControllerGraficoSenzaAccesso implements Initializable {
    @FXML
    private JFXButton attiveButtonName;
    @FXML
    private JFXButton loginButton;
    @FXML
    private JFXButton contattiButton;
    @FXML
    private JFXButton aiutoButton;
    @FXML
    private JFXButton chiSiamoButton;
    @FXML
    private Label menu;
    @FXML
    private Label menuBack;
    @FXML
    private AnchorPane slider;
    //aggiunta ora con il singleton
    @FXML
    private JFXButton risolteButtonName;
    private final ControllerVisualizzatoreScene controllerVisualizzatoreScene = ControllerVisualizzatoreScene.getInstance(null);

    /*questa classe la uso per implementare la logica dei button comuni a tutte le schermate, in particolare questa
     * classe svolge il ruolo di controller grafico per la prova-home.fxml, la quale è la prima schermata che viene
     * quando start l'app*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        slider.setTranslateX(0);
        menu.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);
            slide.setToX(0);
            slide.play();

            slider.setTranslateX(-176);
            slide.setOnFinished((ActionEvent e) -> {
                menu.setVisible(false);
                menuBack.setVisible(true);
            });
        });
        menuBack.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);
            slide.setToX(-176);
            slide.play();

            slider.setTranslateX(0);
            slide.setOnFinished((ActionEvent e) -> {
                menu.setVisible(true);
                menuBack.setVisible(false);
            });
        });
        //if(UtilityAccesso.getNomeUtenteNelDatabase()!=null ){
        //    //setto il nome nella parte account perche significa che l'utente si e' loggato
        //    loginButton.setText(UtilityAccesso.getNomeUtenteNelDatabase());
        //}
        risolteButtonName.setOnMouseClicked(event->{
            if(UtilityAccesso.getNomeUtenteNelDatabase()!=null) {
                //devo vedere se l'utente e' loggato prima di accedere alla pagina
                try {
                    //if(UtilityAccesso.getNomeUtenteNelDatabase()!=null){
                    controllerVisualizzatoreScene.visualizzaScena("Segnalazioni-risolte-page.fxml");
                } catch (Exception e) {
                    System.exit(-1);
                }
            }
        });
        attiveButtonName.setOnMouseClicked(event-> {
            if (UtilityAccesso.getNomeUtenteNelDatabase() != null) {
                try {
                    //if(UtilityAccesso.getNomeUtenteNelDatabase()!=null){
                    //posso accedere
                    controllerVisualizzatoreScene.visualizzaScena("Segnalazioni-attive-page.fxml");
                } catch(Exception e){
                System.exit(-1);
            }
        }
        });

        loginButton.setOnMouseClicked(event -> {
            try {
                if (UtilityAccesso.getNomeUtenteNelDatabase() != null) {
                    //l'utente si e' loggato, voglio quindi caricare la schermata che mi permette di fare il logout
                    controllerVisualizzatoreScene.visualizzaScena("logout-page.fxml");
                } else {
                    //l'utente non è loggato, carico la schermata normale di login
                    //ricorda se l'utente fa il logout le variabili di utility accesso vengono settate a null
                    controllerVisualizzatoreScene.visualizzaScena("login-registrazione-page.fxml");
                }
            }catch (Exception e) {
                    System.exit(-1);
                }
        });
        contattiButton.setOnMouseClicked(event -> {
            try {
                controllerVisualizzatoreScene.visualizzaScena("ContattaciPage.fxml");
            } catch (Exception e) {
                System.exit(-1);
            }
        });
        chiSiamoButton.setOnMouseClicked(event -> {
            try {
                controllerVisualizzatoreScene.visualizzaScena("chiSiamoNoi.fxml");
            } catch (Exception e) {
                System.exit(-1);
            }
        });
        aiutoButton.setOnMouseClicked(event -> {
            try {
                controllerVisualizzatoreScene.visualizzaScena("aiutoAccess.fxml");
            } catch (Exception e) {
                System.exit(-1);
            }
        });
    }
    @FXML
    void SegnalaProblemaAccess() throws Exception {
        controllerVisualizzatoreScene.visualizzaScena("PaginaSegnalaProblema.fxml");
    }

}