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
    // devo usare la classe con attributi globali nelle altre classi che devono vedere se l'utente e' loggato
    //implementa poi la pagina che ha il logout nel caso in cui l'utente gia' ha fatto l'accesso e vuole uscire
    //modifica la pagina del registrati per far inserire uno username all'utente
    //implementa la registrazione con bean controller appilcativo dao query etc etc 
    //fai che quando si registra accede automaticamente
    //fai uscire un messaggio che dice all'utente che la registrazione e il logout sono  andati  a buon fine
    //fai l'encrypt delle password nel database
    // fai le attive
    /*le attive falle cosi, crea dei button jfx tipo  creane 10, metti un pane che posso con una barra laterale cendere
    * e setto a non visible tutti i button, poi per ogni segnalazione setto a visible quel button e quando clicchi sopra al button
    * viene mostrato i numero seriale del palo e l indirizzo e l ostato che viene preso  dal database, se le segnalaziooni attiv e
    * sono superiori a quelle che e' il numero che puo' contenere la pagina crea dinamicamente la barra di scrorrimento
    * tu quella la crei e la metti set visible a false di defaulkt, pii se sonjo piu di 5 la setti a true*/
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