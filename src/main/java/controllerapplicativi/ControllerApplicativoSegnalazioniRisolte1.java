package controllerapplicativi;

import bean.BeanListeElementi;
import dao.SegnalazioniRisolteDao;
import dao.SegnalazioniRisolteDaoImpl;
import eccezioni.ErroreLetturaPasswordException;
import eccezioni.NonEsistonoSegnalazioniException;
import utilityaccesso.UtilityAccesso;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ControllerApplicativoSegnalazioniRisolte1 {
    private int codiceUtente;
    //creo una lista di stringhe che conterranno i numeri seriali dei pali segnalati
    private List<String> listaPaliRiparati=new ArrayList<>();
    private List<String> indirizzoDiQueiPali=new ArrayList<>();
    public ControllerApplicativoSegnalazioniRisolte1(BeanListeElementi beanListeElementi) throws NonEsistonoSegnalazioniException, SQLException, ErroreLetturaPasswordException {
        codiceUtente= Integer.parseInt(UtilityAccesso.getCodiceUtente());
        //devo aggiungere elementi alla lista
        aggiungiElementi(beanListeElementi);
    }
    private void aggiungiElementi(BeanListeElementi bean) throws SQLException, ErroreLetturaPasswordException, NonEsistonoSegnalazioniException {
        SegnalazioniRisolteDao segnalazioniRisolteDao=new SegnalazioniRisolteDaoImpl();
        segnalazioniRisolteDao.cercaSegnalazioniRisolte(listaPaliRiparati,indirizzoDiQueiPali);
        int contatore= listaPaliRiparati.size();
        //questo ciclo non fa altro che riempire le liste dentro il bean
        //qui ci puo' essere l'errore che vengano riempite le stesse liste con valori che in realtà dovrebbero essere diversi
        //nel senso che sia l'applicativo dei risolti che degli attivi usano lo stesso bean e forse questo compromette il tutto
        for(int i=0;i<contatore;i++){
            bean.gestisciListaNumeriSeriali(listaPaliRiparati.get(i));
            bean.gestisciIndirizzi(indirizzoDiQueiPali.get(i));
        }
    }
}
