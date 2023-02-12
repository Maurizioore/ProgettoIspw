package com.example.progettoispw.controllergrafici;
import cli.PaginaHome;
import dao.SingletonConnessione;
import entita.Account;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import utility.Printer;
import utility.UtilityAccesso;
import java.io.*;
import java.sql.SQLException;

public class StartApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        final String schermataPrincipale = "prova-home.fxml";
        ControllerVisualizzatoreScene controllerVisualizzatoreScene = ControllerVisualizzatoreScene.getInstance(stage);
        controllerVisualizzatoreScene.visualizzaScenaPrincipale(schermataPrincipale);
        stage.setOnCloseRequest(windowEvent->{
            windowEvent.consume();
            try{
                logout(stage);
            }catch(SQLException e){
                //qui non viene mai generata un eccezione, non si entra mai in questo catch, nel caso forzo l'uscita
                System.exit(-2);
            }
        });
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
    public void logout(Stage stage) throws SQLException {
        //metodo che si attiva se con l'interfaccia grafica clicco sulla "x" di uscita, avverte l'utente (grazie ad
        //una finestra) che sta uscendo dal sistema e gli fa decidere se uscire oppure no
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("uscita");
        alert.setContentText("vuoi davvero uscire ? ");
        alert.setHeaderText("stai uscendo ");
        if(alert.showAndWait().get()== ButtonType.OK){
            //verifichiamo prima di aver chiuso la connessione con il db se e' stata aperta
            SingletonConnessione.closeConnection();
            //usciamo dall'applicazione
            stage.close();
        }
    }
}