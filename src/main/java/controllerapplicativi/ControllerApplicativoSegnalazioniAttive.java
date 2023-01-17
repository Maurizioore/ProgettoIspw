package controllerapplicativi;

import bean.BeanListeElementi;
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

    public ControllerApplicativoSegnalazioniAttive(BeanListeElementi bean) throws NonEsistonoSegnalazioniException, SQLException, ErroreLetturaPasswordException {
        codiceUtente= Integer.parseInt(UtilityAccesso.getCodiceUtente());
        //devo aggiungere elementi alla lista
        aggiungiElementi(bean);
    }
    private void aggiungiElementi(BeanListeElementi bean) throws SQLException, NonEsistonoSegnalazioniException, ErroreLetturaPasswordException {
        //questa dovra fare una richiesta al db e farsi restituire tutte le segnalazioni associate a quell'utente
        //chiamera' quindi un dao
        SegnalazioniAttiveDao segnalazioniAttiveDao=new SegnalazioniAttiveDaoImpl();
        segnalazioniAttiveDao.cercaSegnalazioniAttive(segnalazioniEffettuateDallUtente,indirizzoSegnalazioneUtente,statoSegnalazioneUtente);
        int contatore= segnalazioniEffettuateDallUtente.size();

        for(int i=0;i<contatore;i++){
            bean.gestisciListaNumeriSeriali(segnalazioniEffettuateDallUtente.get(i));
            bean.gestisciIndirizzi(indirizzoSegnalazioneUtente.get(i));
            bean.gestisciStato(statoSegnalazioneUtente.get(i));
        }
    }

}
