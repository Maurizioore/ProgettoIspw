package controllerapplicativi;


import dao.SegnalazioniAttiveDao;
import dao.SegnalazioniAttiveDaoImpl;
import eccezioni.ErroreLetturaPasswordException;
import eccezioni.NonEsistonoSegnalazioniException;
import utilityaccesso.UtilityAccesso;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ControllerApplicativoSegnalazioniAttive {
    private int codiceUtente;
    //creo una lista di stringhe che conterranno i numeri seriali dei pali segnalati
    private List<String> segnalazioniEffettuateDallUtente=new ArrayList<>();
    private List<String> indirizzoSegnalazioneUtente=new ArrayList<>();
    private List<String> statoSegnalazioneUtente=new ArrayList<>();

    public ControllerApplicativoSegnalazioniAttive() throws NonEsistonoSegnalazioniException, SQLException, ErroreLetturaPasswordException {
        //prendo il codice utente di colui che ha cliccato su attive
        codiceUtente= Integer.parseInt(UtilityAccesso.getCodiceUtente());
        //devo aggiungere elementi alla lista
        aggiungiElementi();
    }
    private void aggiungiElementi() throws SQLException, NonEsistonoSegnalazioniException, ErroreLetturaPasswordException {
        //questa dovra fare una richiesta al db e farsi restituire tutte le segnalazioni associate a quell'utente
        //chiamera' quindi un dao
        SegnalazioniAttiveDao segnalazioniAttiveDao=new SegnalazioniAttiveDaoImpl();
        segnalazioniAttiveDao.cercaSegnalazioniAttive(segnalazioniEffettuateDallUtente,indirizzoSegnalazioneUtente,statoSegnalazioneUtente);


    }

    public List<List<String>> ritornaListaSegnalazioni(){
        //questo metodo deve tornare la lista delle segnalazioni attive al controller grafico
        //devo tornare al controller grafico le 3 liste di sopra, le inserisco quindi in una lista a loro volta
        List<List<String>> resocontoSegnalazione= new ArrayList<>();
        resocontoSegnalazione.add(segnalazioniEffettuateDallUtente);
        resocontoSegnalazione.add(indirizzoSegnalazioneUtente);
        resocontoSegnalazione.add(statoSegnalazioneUtente);
        //ritorno quindi una lista di liste, il controller grafico le dovra scansionare queste liste e prendere i rispettivi valori
        //cree 3 label nella segnalazione
        return  resocontoSegnalazione;
    }


}
