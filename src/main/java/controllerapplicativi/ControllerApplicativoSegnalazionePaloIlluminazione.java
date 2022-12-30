package controllerapplicativi;

import eccezioni.NonEsisteIndirizzoException;
import eccezioni.NonEsisteNumeroSerialeException;
import contenitori.ContenitoreIndirizzi;
import contenitori.ContenitorePaliIlluminazione;
import entita.PaloIlluminazione;

import java.util.ArrayList;


public class ControllerApplicativoSegnalazionePaloIlluminazione {

    /*Questa classe rappresenta il controller applicativo della classe ControllerGraficoSegnalazioneIlluminazione
    * questa classe riceve i dati in input da BeanSegnalazionePaloIlluminazione, il bean avrà svolto i controlli
    * sintattici sui dati, verificare che i dati passati siano corretti spetta a questa classe stessa e lo fa
    * vedendo se l'input passato dall'utente oltre a essere valido è anche corretto, questo avviene grazie
    * all'uso di un contenitore (la cui istanza appartiene alla classe ContenitorePaliIlluminazione) il quale contiene
    * tutti i numeri seriali dei pali dell'illuminazione della zona che l'app deve coprire. Se l'input passato
    * dall'utente è anche veritiero allora questo controller grafico crea un oggetto Palo che deve essere riparato,
    * altrimenti comunica all'utente che non esiste un palo con quel numero seriale.(Discorso duale per l'indirizzo)*/
    private final String numeroSerialePaloIlluminazione;
    private final String indirizzo;
    private ArrayList<String> contenitore=null;
    private ArrayList<String> indirizzi=null;

    public ControllerApplicativoSegnalazionePaloIlluminazione(String numeroSerialePaloIlluminazione, String indirizzo) throws NonEsisteIndirizzoException, NonEsisteNumeroSerialeException {
        this.numeroSerialePaloIlluminazione=numeroSerialePaloIlluminazione;
        this.indirizzo=indirizzo;
        PrendiContenitore();
        VerificaEsistenzaInput();
        //se arrivo qui vuol dire che non c'e' stata alcuna eccezione, devo quindi creare un oggetto palo
        System.out.println("non c'e stata alcuna eccezione: sono una sout presente in ControllerApplicativoSegnalazionePaloIlluminazione");
        PaloIlluminazione paloDaSegnalare= new PaloIlluminazione(numeroSerialePaloIlluminazione,"in corso di riparazione",indirizzo);
    }
    public void PrendiContenitore(){
        //private final String indirizzo;
        ContenitorePaliIlluminazione contenitorePaliIlluminazione = ContenitorePaliIlluminazione.getInstance();
        contenitore= contenitorePaliIlluminazione.ottieniContenitore();
        if (contenitore==null){
            System.out.println("il contenitore dei pali dell'illuminazione e' vuoto");
            System.exit(-1);
        }
        ContenitoreIndirizzi contenitoreIndirizzi= ContenitoreIndirizzi.getInstance();
        indirizzi=contenitoreIndirizzi.ottieniContenitore();
        if (indirizzi==null){
            System.out.println("il contenitore degli indirizzi  e' vuoto");
            System.exit(-1);
        }
    }
    public void VerificaEsistenzaInput() throws NonEsisteIndirizzoException,NonEsisteNumeroSerialeException{
        VerificaPresenzaIndirizzoInputNelContenitore();
        VerificaPresenzaPaloInputNelContenitore();
    }
    private void VerificaPresenzaPaloInputNelContenitore() throws NonEsisteNumeroSerialeException {
        for (String x:contenitore){
            if(x.equals(numeroSerialePaloIlluminazione)){
                //vuol dire che quel palo è valido e può essere segnalato, devo quindi creare un oggetto palo
                //e segnalarlo
                //System.out.println("la procedura di riparazione è stata aperta");
                return;
            }
        }
        //se arrivo qui vuol dire che il numero seriale del palo che ho passato non esiste, lancio quindi un eccezione
        throw new NonEsisteNumeroSerialeException("\nIl numero seriale passato non esiste");
    }
    private void VerificaPresenzaIndirizzoInputNelContenitore() throws NonEsisteIndirizzoException {
        for (String x:indirizzi){
            if(x.equals(indirizzo)){
                //vuol dire che quel palo è valido e può essere segnalato, devo quindi creare un oggetto palo
                //e segnalarlo
                //System.out.println("la procedura di riparazione è stata aperta");
                return;
            }
        }
        //se arrivo qui vuol dire che il numero seriale del palo che ho passato non esiste, lancio quindi un eccezione
        throw new NonEsisteIndirizzoException("\nL'indirizzo passato non esiste");
    }

}
