package com.example.progettoispw.controllergrafici;
import cli.PaginaHome;
import entita.Account;
import javafx.application.Application;
import javafx.stage.Stage;
import utilityaccesso.UtilityAccesso;

import java.io.*;

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
    //metti il login button che appartiene ad uno state e a seconda se l'utente effettua l'accesso o no lui cambia stato e
    //setta il testo al nome utente che ha effettuato l'accesso
    //usa la table view invece della listview
    //ho modificato controller grafico segnalaziomi attive facendolo passare al bean, il codice si interrompe scopri il pefche
    //fai la stessa cosa alle segnalazioni risolte
    //sarebbe utile scrivere nel db in ogni tabella un tipo segnalazione impostato di default a palo nella tabella palo
    //e a buca nella tabella buca, in modo che quando vado a vedere le segnalazioni attive e risolte vedo pure che tipo
    //e' la segnalazione in questione
    //fai un altro controller applicativo per le segnalazioni che sostituisce quello esistente
    //finisci query delle buche, crea un model buca, le buche possono essere segnalate solo da un utente registrato
    //quindi sapendo questo modifica pure il dao delle entità e togli l'else della registrazione delle buche senza accesso
    //prova l equery dell ebuche prima su myql e poi usale qua
    //gestisci eccezioni che si creano con il nuovo cotroller applicativo e il nuovo dao, se metti un palo gia esistente
    //esce fuori un eccezione duplicate entry, per fare questo crea dei bean che traducono il linguaggio macchina in linguaggio umano
    //fai in modo che i paragoni se gli indirizzi sono validi o no avviene nel db, questa cosa la dovra verificare il conroller applicativo
    //segnalazione entita ( poiche il controller applicativo segnalazione palo lo faceva ma vedendo se c'era nei contentitori
    //factory, i controller applicativi la usano per creare il tipo di entita stardale che l'utente vuole segnalare, e viene usata
    //per creare l'account dalla classe utilityAccesso
    @Override
    public void start(Stage stage) throws Exception {
        final String schermataPrincipale = "prova-home.fxml";
        ControllerVisualizzatoreScene controllerVisualizzatoreScene = ControllerVisualizzatoreScene.getInstance(stage);
        controllerVisualizzatoreScene.visualizzaScenaPrincipale(schermataPrincipale);
    }
    public static void main(String[] args) throws IOException {
        //l'app viene lanciata, creiamo quindi un utente di default che possiede come stato di default offline
        UtilityAccesso.setAccount(Account.getInitialAccount());
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("---------------------------------------------------------------------");
        while(true){
            System.out.println("digitare:\n1 per visualizzare l'app con l'interfaccia grafica\n2 per visualizzare l'app in linea di comando");
            String scelta=bufferedReader.readLine();
            try {
                Integer.parseInt(scelta);
            } catch (NumberFormatException e) {
                //di default lancio l'interfaccia grafica
                launch();
                break;
            }
            //l'utente ha inserito effettivamente dei numeri
            int numeroScelta = Integer.parseInt(scelta);
            if(numeroScelta==1) {
                //è stata scelta l'interfaccia grafica
                launch();
                System.exit(0);

            }else if(numeroScelta==2) {
                //è stata scelta la linea di comando
                PaginaHome paginaHome=new PaginaHome();
                paginaHome.displayHomepage();
                System.exit(0);
            }
            System.out.println("mi spiace, prova a digitare 1 oppure 2");
            System.out.println("---------------------------------------------------------------------");
        }

    }
}