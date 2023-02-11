package cli;

import controllergraficicommandlineinterface.ControllerGraficoHome;
import controllergraficicommandlineinterface.ControllerGraficoLoginCli;
import utility.Printer;

import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;

public class PaginaHome {
    private ControllerGraficoLoginCli controllerGraficoLoginCli=new ControllerGraficoLoginCli();
    private ControllerGraficoHome controller = new ControllerGraficoHome();

    public void displayHomepage() throws IOException {

        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));
        Clear.clear();
        Printer.print("------------------------------------------HOME------------------------------------------\n" +
                "digita:\n" +
                "1 per segnalare un problema\n" +
                "2 per lasciare una recensione\n" +
                "3 per suggerire nuove funzioni\n" +
                "4 per effettuare il login\n" +
                "5 per uscire dall'applicazione");
        while(true){
            String scelta=bufferedReader.readLine();
            try {
                Integer.parseInt(scelta);
            } catch (NumberFormatException e) {
                Printer.error("la prossima volta accertati di digitare un numero ");
                System.exit(-1);
            }
            //l'utente ha inserito effettivamente dei numeri
            int numeroScelta = Integer.parseInt(scelta);
            if(numeroScelta == 1){
                this.controller.segnalaProblema();
                break;
            }
            if(numeroScelta == 2){
                Printer.print("la funzione per lasciare una recensione ancora non e' stata implementata, presto sarà disponibile");
                Printer.print("seleziona un'altra funzione");
            }
            if(numeroScelta ==3){
                Printer.print("la funzione per suggerire nuove funzioni ancora non e' stata implementata, presto sarà disponibile");
                Printer.print("seleziona un'altra funzione");
            }
            if(numeroScelta==4){
                //l'utente vuole fare il login
                this.controllerGraficoLoginCli.accediAlSistema();
            }
            if(numeroScelta==5){
                Printer.print("grazie per aver usato l'applicazione, arrivederci =)");
                System.exit(0);
            }
            Printer.print("riprova con uno tra i seguenti numeri : 1, 4 ,5 ");

        }
    }
}
