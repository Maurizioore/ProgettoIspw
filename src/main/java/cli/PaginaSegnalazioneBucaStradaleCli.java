package cli;

import controllergraficicommandlineinterface.ControllerGraficoPagineSegnalazioneBucaStradaleCli;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PaginaSegnalazioneBucaStradaleCli {
    private String indirizzo;
    private int profondita;
    public void inserisciInput() throws IOException {
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("-----------------Pagina Segnalazione Buca Stradale-----------------\n" +
                "inserisci indirizzo(digitare esc per uscire): ");
        this.indirizzo=bufferedReader.readLine();
        System.out.println("inserisci profondità buca in cm(digitare 0 per uscire): ");
        String profondità=bufferedReader.readLine();
        this.profondita=Integer.parseInt(profondità);
        if(profondita>50){
            System.out.println("inserire numeri esatti(nota se supera i 50 cm inserire comunque 50 cm come valore massimo");
            //torna alla home
            tornaAllaHomePage();
        }
        if(verificaInputUscita(indirizzo, profondita)){
            //l'utente vuole tornare alla home
            tornaAllaHomePage();
        }
        //l'utente ha inserito dati corretti, ora questi li passiamo al controller grafico che li passerà a sua volta al bean
        System.out.println("digita:\n1 per salvare la buca segnalata nel database\n2 per salvare la buca segnalata in locale");
        String scelta=bufferedReader.readLine();
        int tipoPersistenza=Integer.parseInt(scelta);
        if(tipoPersistenza!=2){
            //come default imposto di inviarlo al database
            tipoPersistenza=1;
        }
        ControllerGraficoPagineSegnalazioneBucaStradaleCli controllerGraficoPagineSegnalazioneBucaStradaleCli=new ControllerGraficoPagineSegnalazioneBucaStradaleCli(indirizzo,profondita,tipoPersistenza);
        controllerGraficoPagineSegnalazioneBucaStradaleCli.inviaDatiAlBean();
    }
    private boolean verificaInputUscita(String indirizzo, int profondita){
        if(indirizzo.toLowerCase().equals("esc") || profondita==0){
            return true;
        }
        return false;
    }
    private void tornaAllaHomePage() throws IOException {
        PaginaHome paginaHome=new PaginaHome();
        paginaHome.displayHomepage();
    }
}
