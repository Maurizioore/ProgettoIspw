package cli;

import controllergraficicommandlineinterface.ControllerGraficoPagineSegnalazioneBucaStradaleCli;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PaginaSegnalazioneBucaStradaleCli {
    private String indirizzo;
    private int larghezza;
    public void inserisciInput() throws IOException {
        try{
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("-----------------Pagina Segnalazione Buca Stradale-----------------\n" +
                "inserisci indirizzo(digitare esc per uscire): ");
        this.indirizzo=bufferedReader.readLine();
        System.out.println("inserisci la larghezza della buca in cm(digitare 0 per uscire): ");
        String profonditaInserita=bufferedReader.readLine();
        this.larghezza=Integer.parseInt(profonditaInserita);
        if(larghezza>50){
            System.out.println("inserire numeri esatti(nota se supera i 50 cm inserire comunque 50 cm come valore massimo");
            //torna alla home
            tornaAllaHomePage();
        }
        if(verificaInputUscita(indirizzo, larghezza)){
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
        ControllerGraficoPagineSegnalazioneBucaStradaleCli controllerGraficoPagineSegnalazioneBucaStradaleCli=new ControllerGraficoPagineSegnalazioneBucaStradaleCli(indirizzo,larghezza,tipoPersistenza);
        controllerGraficoPagineSegnalazioneBucaStradaleCli.inviaDatiAlBean();
    }catch (NumberFormatException e){
            System.err.println("inserire numeri dove e' richiesto");
            inserisciInput();
        }
    }
    private boolean verificaInputUscita(String indirizzo, int profondita){
        return (indirizzo.equalsIgnoreCase("esc") || profondita==0);

    }
    private void tornaAllaHomePage() throws IOException {
        PaginaHome paginaHome=new PaginaHome();
        paginaHome.displayHomepage();
    }
}
