package controllerapplicativi;

import bean.BeanListeElementi;
import com.example.progettoispw.controllergrafici.TypeOfSegnalazione;
import dao.*;

import eccezioni.ErroreLetturaPasswordException;
import eccezioni.NonEsistonoSegnalazioniException;
import utility.UtilityAccesso;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ControllerApplicativoTipoSegnalazione {
    private int codiceUtente;
    //creo una lista di stringhe che conterranno i numeri seriali dei pali segnalati
    private List<String> segnalazioniPaliEffettuateDallUtente=new ArrayList<>();
    private List<String> indirizzoSegnalazionePaliUtente=new ArrayList<>();
    private List<String> statoSegnalazionePaliUtente=new ArrayList<>();

    private List<String> segnalazioniProfonfitaBucheEffettuateDallUtente=new ArrayList<>();

    private List<String> indirizziBucheSegnalateDallUtente=new ArrayList<>();

    private List<String> statoSegnalazioneBucheUtente=new ArrayList<>();
    private TypeOfSegnalazione typeOfSegnalazione;
    private SegnalazioniRisolteAttiveDao segnalazioniRisolteAttiveDao;



    public ControllerApplicativoTipoSegnalazione(BeanListeElementi bean) throws NonEsistonoSegnalazioniException, SQLException, ErroreLetturaPasswordException {
        codiceUtente= Integer.parseInt(UtilityAccesso.getCodiceUtente());
        typeOfSegnalazione=bean.restituisciTipoSegnalazione();
        //devo aggiungere elementi alla lista che si trova nel bean ce che verrà usata da controller grafico per prendere le info
        aggiungiElementi(bean);
    }
    private void aggiungiElementi(BeanListeElementi bean) throws SQLException, NonEsistonoSegnalazioniException, ErroreLetturaPasswordException {
        //questa dovra fare una richiesta al db e farsi restituire tutte le segnalazioni associate a quell'utente
        //chiamera' quindi un dao
        //in base al tipo di segnalazione crea un dao specifico
        segnalazioniRisolteAttiveDao = new SegnalazioniAttiveRisolteDaoImpl();
        segnalazioniRisolteAttiveDao.cercaSegnalazioni(segnalazioniPaliEffettuateDallUtente,indirizzoSegnalazionePaliUtente,statoSegnalazionePaliUtente,segnalazioniProfonfitaBucheEffettuateDallUtente,indirizziBucheSegnalateDallUtente,statoSegnalazioneBucheUtente,typeOfSegnalazione);
        //conto i pali inseriti
        int contatore= segnalazioniPaliEffettuateDallUtente.size();
        //conto le buche inserite
        int contatore2=segnalazioniProfonfitaBucheEffettuateDallUtente.size();
        //riempio i pali del bean
        for(int i=0;i<contatore;i++){
            bean.gestisciListaNumeriSeriali(segnalazioniPaliEffettuateDallUtente.get(i));
            bean.gestisciIndirizzi(indirizzoSegnalazionePaliUtente.get(i));
            bean.gestisciStato(statoSegnalazionePaliUtente.get(i));
        }
        //riempio le buche del bean
        for(int i=0;i<contatore2;i++){
            bean.gestisciListaProfonditaBucaStradale(segnalazioniProfonfitaBucheEffettuateDallUtente.get(i));
            bean.gestisciIndirizziBuca(indirizziBucheSegnalateDallUtente.get(i));
            bean.gestisciStatoBucaStradale(statoSegnalazioneBucheUtente.get(i));
        }

    }

}