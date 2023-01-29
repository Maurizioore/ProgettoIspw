package com.example.progettoispw.controllergrafici;
import bean.BeanLogin;
import com.jfoenix.controls.JFXButton;
import controllerapplicativi.ControllerApplicativoLoginAlSistema;
import eccezioni.ErroreLetturaPasswordException;
import eccezioni.NonEsisteUtenteNelSistemaException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControllerGraficoLoginPage extends ControllerGraficoGenerale {

    //aggiunta ora con il singleton
    private final ControllerVisualizzatoreScene controllerVisualizzatoreScene=ControllerVisualizzatoreScene.getInstance(null);
    @FXML
    private JFXButton registratiButton;
    @FXML
    private TextField emailTextField;
    @FXML
    private PasswordField passwordPasswordField;
    @FXML
    private JFXButton accediButton;
    @FXML
    private Label labelComunicazione;

    private BeanLogin beanAccessoUtente;

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

        //se l'utente vuole registrarsi ( e clicca quindi su registrati) verrà caricata questa schermata
        registratiButton.setOnMouseClicked(event->{
            try {
                controllerVisualizzatoreScene.visualizzaScena("registrazione-page.fxml");
            }catch(Exception e){
                System.out.println("error: "+ e);
            }
        });
        //se l'utente possiede un account, compila e due textField con e mail e password e clicca su accedi, viene quindi
        //eseguito il codice associato ad accediAlTuoAccountButton
        accediButton.setOnMouseClicked(event->{
            //prima verifico che entrambi i campi (email e password siano stati compilati)
            if (emailTextField.getText().equals("") ||  passwordPasswordField.getText().equals("")) {
                labelComunicazione.setText("inserire entrambi i campi");
            }else {
                //i campi sono stati entrambi passati e passo al bean questi dati
                beanAccessoUtente = new BeanLogin(emailTextField.getText(), passwordPasswordField.getText());
                //svolgo prima i controlli sulla email inserita dall'utente, verifico cioè se è sintatticamente corretta
                String controlliSintatticiEmail = beanAccessoUtente.svolgiControlli();
                //se l'email è sintatticamente corretta vado avanti altrimenti counico l'errore all'utente
                if (controlliSintatticiEmail == null) {
                    //mando il bean al controller applicativo
                    try {
                        ControllerApplicativoLoginAlSistema controllerApplicativoLoginAlSistema = new ControllerApplicativoLoginAlSistema(beanAccessoUtente);
                        // se non si e' verificata nessuna eccezione vuol dire che l'accesso e' stato effettuato con successo
                        emailTextField.setDisable(true);
                        passwordPasswordField.setDisable(true);
                        accediButton.setDisable(true);
                        registratiButton.setDisable(true);
                        labelComunicazione.setText("accesso effettuato con successo");

                    }catch(SQLException | NonEsisteUtenteNelSistemaException | ErroreLetturaPasswordException e){
                         labelComunicazione.setText(e.getMessage());
                    }
                }else{
                    labelComunicazione.setText(controlliSintatticiEmail);
                }
            }
        });
        //questo avrà i suoi pulsanti e alla fine chiamerà il super di quelli in comune, per non darli al figlio
        //quelli che non sono in comune dovrei farli private
        super.initialize(url,resourceBundle);
        /*ora devo settare i nomi agli altri pulsanti, devo dare l'accesso i controllerGraficoSenzaAccesso e collegarlo
        * qui come quelli di sopra, ovviamente accedi e registrati non devo metterli in ControllerGraficoSenzaAccessi
        * ma devo metterli dentro questa classe come ho fatto per indietroButton, dovrò creare quindi un AccediButton e un
        * registratiButton e fare come ho fatto per indietroButton dando cosa deve accadere quando clicco con mouse */
    }
}
