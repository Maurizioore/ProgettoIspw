package controllerapplicativi;

import bean.BeanSegnalazionePaloIlluminazione;
import contenitori.Contenitore;
import dao.EntitaStradaleDao;
import eccezioni.ErroreLetturaPasswordException;
import eccezioni.NonEsisteIndirizzoException;
import eccezioni.NonEsisteNumeroSerialeException;
import contenitori.ContenitoreIndirizzi;
import contenitori.ContenitorePaliIlluminazione;
import eccezioni.SegnalazioneGiaAvvenutaException;
import entita.EntitaStradale;
import entita.PaloIlluminazione;
import java.sql.SQLException;
import java.util.List;


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
    private List<String> contenitore=null;
    private List<String> indirizzi=null;

    public ControllerApplicativoSegnalazionePaloIlluminazione(BeanSegnalazionePaloIlluminazione beanSegnalazionePaloIlluminazione) throws SQLException, SegnalazioneGiaAvvenutaException, ErroreLetturaPasswordException {

        this.numeroSerialePaloIlluminazione=beanSegnalazionePaloIlluminazione.getNumeroSerialePalo();
        this.indirizzo=beanSegnalazionePaloIlluminazione.getIndirizzo();
        //se arrivo qui vuol dire che non c'e' stata alcuna eccezione, devo quindi creare un oggetto palo
        System.out.println("non c'e stata alcuna eccezione: sono una sout presente in ControllerApplicativoSegnalazionePaloIlluminazione");
        //questa variabile paloDaSegnalare sonar cloud dice di toglierla perché non viene mai acceduta, la lascio perche non so se mi
        //servirà per avere il numero segnalazione del palo corrente
        EntitaStradale paloDaSegnalare= new PaloIlluminazione(numeroSerialePaloIlluminazione,indirizzo);
        //questa la devo mandare al db
        inviaSegnalazione((PaloIlluminazione) paloDaSegnalare);
    }
    public void inviaSegnalazione(PaloIlluminazione palo) throws SQLException, SegnalazioneGiaAvvenutaException, ErroreLetturaPasswordException {
        //EntitaStradaleDao entitaStradaleDao= new EntitaStradaleDaoImpl();
        //entitaStradaleDao.saveEntitaStradale(palo);
        //invia segnalazione lo fa un altro controller applicativo 

    }
    public void prendiContenitore(){
        Contenitore contenitorePaliIlluminazione = ContenitorePaliIlluminazione.getInstance();
        contenitore= contenitorePaliIlluminazione.ottieniContenitore();
        if (contenitore==null){
            //il contenitore pali illuminazione e' vuoto
            System.exit(-1);
        }
        Contenitore contenitoreIndirizzi= ContenitoreIndirizzi.getInstance();
        indirizzi=contenitoreIndirizzi.ottieniContenitore();
        if (indirizzi==null){
            //il contenitore degli indirizzi e' vuoto 
            System.exit(-1);
        }
    }
    public void verificaEsistenzaInput() throws NonEsisteIndirizzoException,NonEsisteNumeroSerialeException{
        verificaPresenzaIndirizzoInputNelContenitore();
        verificaPresenzaPaloInputNelContenitore();
    }
    private void verificaPresenzaPaloInputNelContenitore() throws NonEsisteNumeroSerialeException {
        for (String x:contenitore){
            if(x.equals(numeroSerialePaloIlluminazione)){
                //vuol dire che quel palo è valido e può essere segnalato, devo quindi creare un oggetto palo
                //e segnalarlo
                return;
            }
        }
        //se arrivo qui vuol dire che il numero seriale del palo che l'utente ha  passato non esiste, lancio quindi un eccezione
        throw new NonEsisteNumeroSerialeException("\nIl numero seriale passato non esiste");
    }
    private void verificaPresenzaIndirizzoInputNelContenitore() throws NonEsisteIndirizzoException {
        for (String x:indirizzi){
            if(x.equals(indirizzo)){
                //vuol dire che quelL'indirizzo è valido e può essere segnalato, devo quindi creare un oggetto palo
                //e segnalarlo
                return;
            }
        }
        //se arrivo qui vuol dire che l'indirizzo che l'utente ha  passato non esiste, lancio quindi un eccezione
        throw new NonEsisteIndirizzoException("\nL'indirizzo passato non esiste");
    }

}
