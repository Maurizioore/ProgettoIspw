package controllerapplicativi;

import bean.BeanSegnalaEntita;
import dao.*;
import eccezioni.ErroreLetturaPasswordException;
import eccezioni.NessunAccessoEffettuatoException;
import eccezioni.SegnalazioneGiaAvvenutaException;
import eccezioni.TipoEntitaException;
import entita.EntitaStradale;
import factory.FactoryDao;
import factory.FactoryEntitaStradale;
import factory.TypeEntita;
import factory.TypeOfPersistence;
import utilityaccesso.UtilityAccesso;

import java.io.IOException;
import java.sql.SQLException;

public class ControllerApplicativoSegnalazioneEntita {
    /*questo controller applicativo viene chiamato quanod sto inviando una nuova segnalazione di un entita al db,
    * crea quindi un entita dai dati che riceve, chiama il dao che gestisce la dovuta segnalazione e in caso di errori
    * lancia delle eccezioni, al controller grafico non ritorna nulla */
    private  String indirizzo;
    private  String infoEntita;
    private TypeEntita tipoEntita;
    private EntitaStradale entitaStradale;
    public ControllerApplicativoSegnalazioneEntita(BeanSegnalaEntita beanSegnalaEntita) throws SQLException, ErroreLetturaPasswordException, SegnalazioneGiaAvvenutaException, NessunAccessoEffettuatoException, TipoEntitaException, IOException {
        /*il controller applicativo riceve il bean che contiene le informazioni dell'entita segnalata, setta quindi tutti i
        * suoi parametri prendendoli dal bean*/
        //inizio prendendo il tipo dell'entità segnalata
        this.tipoEntita=beanSegnalaEntita.getTipoEntitaSegnalata();
        //vedo se è una buca e se l'utente è online, cosi in caso contrario lo blocco subito
        if(tipoEntita==TypeEntita.BUCASTRADALE && UtilityAccesso.getAccount().getStatoAttuale().equals("OFFLINE")){
            //l'utente cerca di segnalare una buca ma non e' registrato quindi viene lanciata un eccezione che gli
            //dice che la segnalazione della buca puo' essere fatta solo se registrato
            throw new NessunAccessoEffettuatoException("per segnalare una buca devi essere registrato");
        }
        this.indirizzo=beanSegnalaEntita.getIndirizzo();
        this.infoEntita=beanSegnalaEntita.getInfoEntita();
        //creo la factory che deve creare a sua volta la mia entita in base al tipo
        FactoryEntitaStradale factoryEntitaStradale=new FactoryEntitaStradale();
        //chiamo il metodo createEntita che in base al tipo che gli passo crea un palo dell'illuminazione o una buca stradale
        entitaStradale=factoryEntitaStradale.createEntita(this.tipoEntita,this.indirizzo,this.infoEntita);
        //nessuna eccezione creata, l'entita stradale e' stata quindi creata, devo inviarla alla rispettiva tabella nel db
        System.out.println(entitaStradale.getClass());
        //nessuna eccezione devo inviare l'entità stradale segnalata e per capire se deve essere salvata sul db o in locale
        //passo come argomento il tipo di persistenza che il controller grafico ha inserito nel mio bean nel momento della segnalazione
        inviaSegnalazione(entitaStradale,beanSegnalaEntita.getTypeOfPersistence());
    }

    private void inviaSegnalazione(EntitaStradale entitaStradale, TypeOfPersistence typeOfPersistence) throws SQLException, ErroreLetturaPasswordException, SegnalazioneGiaAvvenutaException, IOException {
        //creo una factoryDao la quale ha solo il metodo useDao e mi restituisce un dao in base al tipo di entita stradale e al
        //tipo di persistenza che ho ricevuto come parametri
        FactoryDao factoryDao=new FactoryDao();
        EntitaStradaleDao entitaStradaleDao=factoryDao.useDao(typeOfPersistence,entitaStradale.getTypeEntita());
        entitaStradaleDao.saveEntitaStradale(entitaStradale);
    }
}
