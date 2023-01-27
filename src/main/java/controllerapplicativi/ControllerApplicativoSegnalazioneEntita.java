package controllerapplicativi;

import bean.BeanSegnalaEntita;

import dao.*;
import eccezioni.ErroreLetturaPasswordException;
import eccezioni.NessunAccessoEffettuatoException;
import eccezioni.SegnalazioneGiaAvvenutaException;
import eccezioni.TipoEntitaException;
import entita.BucaStradale;
import entita.EntitaStradale;
import entita.PaloIlluminazione;
import factory.FactoryEntitaStradale;
import factory.TypeEntita;
import utilityaccesso.UtilityAccesso;

import java.sql.SQLException;

public class ControllerApplicativoSegnalazioneEntita {
    /*questo controller applicativo viene chiamato quanod sto inviando una nuova segnalazione di un entita al db,
    * crea quindi un entita dai dati che riceve, chiama il dao che gestisce la dovuta segnalazione e in caso di errori
    * lancia delle eccezioni, al controller grafico non ritorna nulla */
    private   String indirizzo;
    private  String infoEntita;
    //private  String tipoEntita;
    private TypeEntita tipoEntita;
    private EntitaStradale entitaStradale;
    public ControllerApplicativoSegnalazioneEntita(BeanSegnalaEntita beanSegnalaEntita) throws SQLException, ErroreLetturaPasswordException, SegnalazioneGiaAvvenutaException, NessunAccessoEffettuatoException, TipoEntitaException {
        /*il controller applicativo riceve il bean che contiene le informazioni dell'entita segnalata, setta quindi tutti i
        * suoi parametri prendendoli dal bean*/
        //inizio prendendo il tipo dell'entità segnalata
        this.tipoEntita=beanSegnalaEntita.getTipoEntitaSegnalata();
        //vedo se è una buca e se l'utente è online, cosi in caso contrario lo blocco subito
        if(tipoEntita==TypeEntita.type_buca_stradale && UtilityAccesso.getAccount().getStatoAttuale().equals("OFFLINE")){
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
        inviaSegnalazione(entitaStradale);

        //creo ora l' entita
        //if(tipoEntita.equals("PaloIlluminazione")){
        //    //mi e' arrivata la segnalazione di un palo dell'illuminazione, un palo può essere segnalato da chiunque
        //    //sia utente registrato che non, quindi non ho alcun problema
        //    entitaStradale=new PaloIlluminazione(infoEntita,indirizzo);
        //}else{
        //    //mi e' arrivata la segnalazione di una buca dal controller grafico della segnalazuoen delle dbuche
        //    if(UtilityAccesso.getAccount().getStatoAttuale()=="OFFLINE") {
        //        //le segnalazioni delle buche possono essere fatte solo da utenti registrati al sistema e che sono in uno stato ONLINE, se l'utente non
        //        //è online non può segnalare una buca e riceve un eccezione
        //        throw new NessunAccessoEffettuatoException("per segnalare una buca devi essere registrato");
        //    }
        //    entitaStradale= new BucaStradale(infoEntita,indirizzo);
        //}
        //System.out.println(entitaStradale.getClass());
        //inviaSegnalazione(entitaStradale);
    }

    private void inviaSegnalazione(EntitaStradale entitaStradale) throws SQLException, ErroreLetturaPasswordException, SegnalazioneGiaAvvenutaException {
        //questa è sbagliata, un dao per ogni tabella, quindi sarà invia segnalazione che deve vedere di che tipo è e mandarla
        //la segnalazione al corrispettivo dao dell'entita
        if (entitaStradale.getClass() == PaloIlluminazione.class) {
            //l'utente ha mandato la segnalazione di un palo, quindi mando questa enttia stradale al dao che gestisce i pali
            PaloIlluminazioneDao paloIlluminazioneDao=new PaloIlluminazioneDaoImpl();
            //faccio il cast da entita stradale a palo illuminazione, dato che il dao dei pali illuminazione gestisce PaliIlluminazione e non entitaStradale
            PaloIlluminazione paloIlluminazioneDaSegnalare=new PaloIlluminazione(entitaStradale.infoEntita(),entitaStradale.getIndirizzo());
            System.out.println("sono una sout present in controller applicativo segnalazione entita, nella parte invia segnalazione. voglio vedere che il cast sia riuscito,\nnumeroSeriale: "+paloIlluminazioneDaSegnalare.numeroSerialePalo+"\nindirizzo: "+paloIlluminazioneDaSegnalare.indirizzo);
            paloIlluminazioneDao.savePaloIlluminazione(paloIlluminazioneDaSegnalare);
        }else{
            //l'utente ha segnalato una buca, devo mandare l'entita stradale al dao che gestisce le buche, i controlli
            //se l'utente può oppure no inviare una buca (cioè se si trova in uno stato ONLINE, sono stati già fatti, quindi
            //qui mi devo solo preoccupare d'inviare la buca segnalata al rispettivo dao
            BucaStradaleDao bucaStradaleDao=new BucaStradaleDaoImpl();
            BucaStradale bucaStradaleDaSegnalare= new BucaStradale(entitaStradale.infoEntita(),entitaStradale.getIndirizzo());
            System.out.println("sono una sout present in controller applicativo segnalazione entita, nella parte invia segnalazione. voglio vedere che il cast sia riuscito,\nindirizzo: "+bucaStradaleDaSegnalare.indirizzo+"\nprofondita: "+bucaStradaleDaSegnalare.profondita);
            bucaStradaleDao.saveBucaStradale(bucaStradaleDaSegnalare);
        }
    }
}
