package com.example.progettoispw.controllergrafici;
import cli.PaginaHome;
import entita.Account;
import javafx.application.Application;
import javafx.stage.Stage;
import utility.Printer;
import utility.UtilityAccesso;

import java.io.*;

public class StartApplication extends Application {
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
        Printer.print("---------------------------------------------------------------------");
        while(true){
            Printer.print("digitare:\n1 per visualizzare l'app con l'interfaccia grafica\n2 per visualizzare l'app in linea di comando");
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
            Printer.print("mi spiace, prova a digitare 1 oppure 2");
            Printer.print("---------------------------------------------------------------------");
        }

    }
}