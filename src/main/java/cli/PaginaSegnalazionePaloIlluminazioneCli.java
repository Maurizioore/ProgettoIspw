package cli;

import controllergraficicommandlineinterface.ControllerGraficoPaginaSegnalazionePaloIlluminazioneCli;
import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;

public class PaginaSegnalazionePaloIlluminazioneCli {
    private String numeroSerialePalo;
    private String indirizzo;
    private ControllerGraficoPaginaSegnalazionePaloIlluminazioneCli controllerGraficoPaginaSegnalazionePaloIlluminazioneCli;
    public void inserisciInput() throws IOException{
        Clear.clear();
        System.out.println("------------------Pagina Segnalazione Palo Illuminazione----------------\n" +
                "inserisci numero seriale palo da segnalare( digitare esc per uscire): ");
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));
        numeroSerialePalo=bufferedReader.readLine();
        System.out.println("inserisci indirizzo (digitare esc per uscire): ");
        indirizzo=bufferedReader.readLine();
        //prima verifichiamo se l'utente vuole uscire dalla schermata
        if(verificaInputUscita(numeroSerialePalo, indirizzo)){
            tornaAllaHomePage();
        }else if(numeroSerialePalo.equals("")|| indirizzo.equals("")){
            System.out.println("la prossima volta inserisci qualcosa");
            tornaAllaHomePage();
        }
        else {
            //l'utente non vuole uscire, invio i dati al bean
            //invio i dati al controller grafico della pagina segnalazione problema il quale li invia al bean
            System.out.println("digita:\n1 se vuoi inviare la segnalazione al database\n2 se vuoi salvarla in locale");
            try {
                String scelta = bufferedReader.readLine();
                int tipoPersistenza = Integer.parseInt(scelta);
                if (tipoPersistenza != 2) {
                    //come default imposto di inviarlo al database
                    tipoPersistenza = 1;
                }
                controllerGraficoPaginaSegnalazionePaloIlluminazioneCli = new ControllerGraficoPaginaSegnalazionePaloIlluminazioneCli(numeroSerialePalo, indirizzo, tipoPersistenza);
                controllerGraficoPaginaSegnalazionePaloIlluminazioneCli.inviaDatiAlBean();
            }catch(NumberFormatException e){
                //si verifica se l'utente non digita un numero
                System.err.println("digitare solo un numero tra 1 e 2");
                inserisciInput();
            }
        }
    }
    private boolean verificaInputUscita(String numeroSerialePalo, String indirizzo){
        return(numeroSerialePalo.equalsIgnoreCase("esc") || indirizzo.equalsIgnoreCase("esc"));

    }
    private void tornaAllaHomePage() throws IOException {
        PaginaHome paginaHome=new PaginaHome();
        paginaHome.displayHomepage();
    }
}
