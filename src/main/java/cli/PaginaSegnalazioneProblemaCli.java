package cli;

import utility.Printer;

import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;

public class PaginaSegnalazioneProblemaCli {
    private int numeroScelta;
    private PaginaHome paginaHome;
    private PaginaSegnalazionePaloIlluminazioneCli paginaSegnalazionePaloIlluminazioneCli;
    private PaginaSegnalazioneBucaStradaleCli paginaSegnalazioneBucaStradaleCli;

    public void mostraProblemiChePossonoEssereSegnalati() throws IOException {
        Clear.clear();
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));
        Printer.print("------------------------------------------Pagina Segnalazione Problema------------------------------------------\n" +
                "digita:\n" +
                "1 per segnalare un palo dell'illuminazione\n" +
                "2 per segnalare una buca stradale\n" +
                "qualsiasi altro input se vuoi tornare alla home");
        String scelta=bufferedReader.readLine();
        try {
            numeroScelta = Integer.parseInt(scelta);
        } catch (NumberFormatException e) {
            Printer.error("Input non valido. Inserisci solo numeri.");
        }
        if (numeroScelta == 1) {
            //sta segnalando un palo dell'illuminazione
            this.paginaSegnalazionePaloIlluminazioneCli=new PaginaSegnalazionePaloIlluminazioneCli();
            paginaSegnalazionePaloIlluminazioneCli.inserisciInput();
        }else if(numeroScelta == 2) {
            //sta segnalando una buca stradale
            this.paginaSegnalazioneBucaStradaleCli=new PaginaSegnalazioneBucaStradaleCli();
            paginaSegnalazioneBucaStradaleCli.inserisciInput();
        }else{
            tornaAllaHome();
        }
    }
    private void tornaAllaHome() throws IOException {
        paginaHome=new PaginaHome();
        paginaHome.displayHomepage();
    }

}
