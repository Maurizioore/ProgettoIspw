package com.example.progettoispw.controllergrafici;

import bean.BeanImpostazioni;
import com.jfoenix.controls.JFXButton;
import controllerapplicativi.ControllerApplicativoSerializzazione;
import eccezioni.EtaException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerGraficoImpostazioni implements Initializable {
    ControllerVisualizzatoreScene controllerVisualizzatoreScene = ControllerVisualizzatoreScene.getInstance(null);
    @FXML
    private JFXButton ritornaHomeButton;
    @FXML
    private JFXButton modificaSessoButton;
    @FXML
    private JFXButton modificaNomeButton;
    @FXML
    private JFXButton modificaCognomeButton;
    @FXML
    private JFXButton modificaEtaButton;
    @FXML
    private JFXButton salvaButton;
    @FXML
    private TextField textFieldCognome;
    @FXML
    private TextField textFieldNome;
    @FXML
    private TextField textFieldEta;
    @FXML
    private TextField textFieldSesso;
    @FXML
    private Label labelErrore;
    @FXML
    private Label labelNome;
    @FXML
    private Label labelCognome;
    @FXML
    private Label labelEta;
    @FXML
    private Label labelSesso;
    private String nome;
    private String cognome;
    private String sesso;
    private int eta;

    private BeanImpostazioni beanImpostazioni=new BeanImpostazioni();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //devo chiedere al database tutte le segnalazioni che quell'utente ha effettuato, quindi le chiedo ad un
        //controller applicativo
        //volendo potrei mettere un button chiamato mostraSeganalazioni e mettere questo codice nel
        //mostraSegnalazioni.setOnMouseClicked , almeno se sonar cloud da errore lo risolviamo
        try {
            //appena entro vedo se esiste un file.ser creato
            ControllerApplicativoSerializzazione controllerApplicativoSerializzazione=new ControllerApplicativoSerializzazione();
            controllerApplicativoSerializzazione.prendiInformazioni(beanImpostazioni);
            //se non ha lanciato eccezzioni mi aspetto che mi abbia restituito il contenuto in nel bean che gli passo
            salvaModifichePresenti(beanImpostazioni);
        }catch(IOException | ClassNotFoundException e){
           labelErrore.setText(e.getMessage());
        }
        modificaNomeButton.setOnMouseClicked(event->{
            rendiInvisibiliLabel(labelNome);
            rendiVisibiliTextField(textFieldNome);
        });
        modificaCognomeButton.setOnMouseClicked(event->{
            rendiInvisibiliLabel(labelCognome);
            rendiVisibiliTextField(textFieldCognome);
        });
        modificaEtaButton.setOnMouseClicked(event->{
            rendiInvisibiliLabel(labelEta);
            rendiVisibiliTextField(textFieldEta);
        });
        modificaSessoButton.setOnMouseClicked(event->{
            rendiInvisibiliLabel(labelSesso);
            rendiVisibiliTextField(textFieldSesso);
        });
        salvaButton.setOnMouseClicked(event -> {
            try {
                //se l'utente ha commesso un errore prima ora rimuovo la stringa di errore
                //labelErrore.setText(" ");
                //immagino di aver inserito qualcosa
                applicaModifiche();
                //sono state apportate delle modifiche devo salvarle nell'oggetto serializable
                beanImpostazioni = new BeanImpostazioni(nome, cognome, eta, sesso);
                //questo bean lo devo passare al controller che si occupa di serializzare l'oggetto
                ControllerApplicativoSerializzazione controllerApplicativoSerializzazione = new ControllerApplicativoSerializzazione(beanImpostazioni);
                controllerApplicativoSerializzazione.salvaInformazioni();
            } catch (IOException e) {
                labelErrore.setText(e.getMessage());
            } catch(EtaException e){
                labelErrore.setText(e.getMessage());
            }
        });
        ritornaHomeButton.setOnMouseClicked(event -> {
            try {
                controllerVisualizzatoreScene.visualizzaScena("prova-home.fxml");
            } catch (Exception e) {
                System.exit(-1);
            }
        });
    }

    public void applicaModifiche() throws EtaException{
        try {
            nome = textFieldNome.getText();
            cognome = textFieldCognome.getText();
            eta = Integer.parseInt(textFieldEta.getText());
            if( eta<=0 || eta >=100){
                throw new EtaException("inserire un età compresa tra 0 e 100");
            }
            sesso = textFieldSesso.getText();
            //adesso devo serializzare le modifiche
            beanImpostazioni=new BeanImpostazioni(nome,cognome,eta,sesso);
            ControllerApplicativoSerializzazione controllerApplicativoSerializzazione=new ControllerApplicativoSerializzazione(beanImpostazioni);
            controllerApplicativoSerializzazione.salvaInformazioni();
            rendiInvisibiliTextField();
            labelEta.setText(Integer.toString(eta));
            labelNome.setText(nome);
            labelCognome.setText(cognome);
            labelSesso.setText(sesso);
            rendiVisibiliLabel();
        } catch (NumberFormatException e) {
            throw new EtaException("inserire un numero tra 0 e 100 nella sezione età");
        } catch (IOException e){
            labelErrore.setText(e.getMessage());
        }
    }
    private void salvaModifichePresenti(BeanImpostazioni beanImpostazioni){
        rendiInvisibiliTextField();
        //imposto nome cognome eta e sesso con quelli che ho preso dal file, li metto come testo delle label
        labelNome.setText(beanImpostazioni.getNome());
        labelCognome.setText(beanImpostazioni.getCognome());
        labelEta.setText(Integer.toString(beanImpostazioni.getEta()));
        labelSesso.setText(beanImpostazioni.getSesso());
        //imposto le variabili della classe con il valore delle stesse variabili presenti nel bean
        this.nome=beanImpostazioni.getNome();
        this.cognome=beanImpostazioni.getCognome();
        this.eta=beanImpostazioni.getEta();
        this.sesso=beanImpostazioni.getSesso();
        //imposto questi valori anche ai textField così che in caso di modifica su un solo attributo gli altri rimangono con lo stesso valore e non ho problemi in caso
        //di aggiornamento di singoli attributi
        textFieldNome.setText(nome);
        textFieldCognome.setText(cognome);
        textFieldEta.setText(Integer.toString(eta));
        textFieldSesso.setText(sesso);
        //rendo visibili le label
        rendiVisibiliLabel();


    }
    private void rendiInvisibiliLabel(Label l){
        l.setVisible(false);
    }
    private void rendiInvisibiliTextField(){
        textFieldCognome.setVisible(false);
        textFieldNome.setVisible(false);
        textFieldSesso.setVisible(false);
        textFieldEta.setVisible(false);
    }
    private void rendiVisibiliLabel(){
        labelEta.setVisible(true);
        labelCognome.setVisible(true);
        labelNome.setVisible(true);
        labelSesso.setVisible(true);
    }
    private void rendiVisibiliTextField(TextField textField){
        textField.setVisible(true);
    }
}
