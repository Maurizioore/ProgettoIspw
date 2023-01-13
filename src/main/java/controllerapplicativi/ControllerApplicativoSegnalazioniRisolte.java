package controllerapplicativi;

import dao.SegnalazioniRisolteDao;
import dao.SegnalazioniRisolteDaoImpl;
import eccezioni.ErroreLetturaPasswordException;
import eccezioni.NonEsistonoSegnalazioniException;
import utilityaccesso.UtilityAccesso;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ControllerApplicativoSegnalazioniRisolte {
    /*questo controller applicativo dovrà:
    -usare la dao per fare le dovute richieste, in questo caso chiederà solo la sista delle segnalazioni associate
    all'utente che sono terminate( per capire a quale utente sono associate le segnalazioni il costruttore di
    questo controller applicativo setterà il codice utente dell'utente che si e' loggato
    -restituire al controller grafico la lista delle segnalazioni che per quell'utente sono terminate
     */
    private int codiceUtente;
    //queste 2 liste contengono l'insieme dei pali che sono stati riparati e gli indirizzi in cui ogni palo si trova

    public List<String> listaPaliRiparati= new ArrayList<>();
    public List<String> indirizzoDiQueiPali= new ArrayList<>();
    public ControllerApplicativoSegnalazioniRisolte() throws SQLException, NonEsistonoSegnalazioniException, ErroreLetturaPasswordException {
        //prendo il codice utente di colui che ha cliccato su risolte
        codiceUtente= Integer.parseInt(UtilityAccesso.getCodiceUtente());
        //devo aggiungere elementi alla lista
        aggiungiSegnalazioniAndateABuonFine();
    }
    private void aggiungiSegnalazioniAndateABuonFine() throws SQLException, NonEsistonoSegnalazioniException, ErroreLetturaPasswordException {
        //qui avviene la comunicazione con il dao che
        //-aprirà una connessione con il database
        //-interrogherà il db per ricevere le segnalazioni che hanno terminato
        SegnalazioniRisolteDao segnalazioniRisolteDao=new SegnalazioniRisolteDaoImpl();
        //passo al metodo le 2 liste locali che verranno riempite con l'insieme dei pali riparati e i loro indirizzi
        segnalazioniRisolteDao.cercaSegnalazioniRisolte(listaPaliRiparati,indirizzoDiQueiPali);

    }
    public List<List<String>> ritornaListaSegnalazioni(){
        //restituisco al controller grafico queste liste di pali e indirizzi, mettendoli d a loro volta dentro una lisat di
        //liste, sarà il controller grafico che si preoccuperà di scomporre questa lista di liste
        List<List<String>> resocontoSegnalazione= new ArrayList<>();
        resocontoSegnalazione.add(listaPaliRiparati);
        resocontoSegnalazione.add(indirizzoDiQueiPali);
        return resocontoSegnalazione;
    }
}
