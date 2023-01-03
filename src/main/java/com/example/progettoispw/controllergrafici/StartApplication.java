package com.example.progettoispw.controllergrafici;
import javafx.application.Application;
import javafx.stage.Stage;

public class StartApplication extends Application {

    //non mi piace l'instanziamento di 50 mila Controller Visualizzatore scene, riprendi quando hai fatto la singleton
    //adesso devo creare la pagina segnala palo e sotto di esso segnala buca
    //fai extend alle classi cgasl cgpsp cglp su  controller grafico senza accesso
    //in controller grafico senza accesso togli il commento a quelli in initilizable
    //e  alle classi cgasl cgpsp cglp fai chiamare super initilizable
    //visto che ci stai lascia solo il controller visulizzatore scene del padre e togli ai figli che lo estendono
    //devo creare il palo quando la segnalazione va a buon fine, fare i controlli sull'indirizzo con un contenitore
    // creare il palo in modo che posso cambiare dinamicamente il suo stato e comunicare con un db ect ect ect
    // crea il login la tabella del login e senti audio che ti sei mandato

    @Override
    public void start(Stage stage) throws Exception {
        final String schermataPrincipale="prova-home.fxml";
        ControllerVisualizzatoreScene controllerVisualizzatoreScene=ControllerVisualizzatoreScene.getInstance(stage);
        controllerVisualizzatoreScene.visualizzaScenaPrincipale(schermataPrincipale);
    }
    public static void main(String[] args) {
        launch();
    }
}