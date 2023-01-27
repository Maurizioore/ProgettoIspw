package factory;

import eccezioni.TipoEntitaException;
import entita.BucaStradale;
import entita.EntitaStradale;
import entita.PaloIlluminazione;

public class FactoryEntitaStradale {
    //questa factory in base al tipo che riceve il suo unico metodo crea un entita stradale e la restituisce a chi la chiama
    public EntitaStradale createEntita(TypeEntita tipoEntitaSegnalata,String indirizzo,String infoEntita) throws TipoEntitaException {
        //faccio uno switch case in base al tipo di entità che ricevo
        switch (tipoEntitaSegnalata){
            case type_buca_stradale:
                //ho ricevuto un Type_buca_Stradale, creo e ritorno una buca stradale
                return new BucaStradale(infoEntita,indirizzo);
            case type_palo_illuminazione:
                //ho ricevuto un palo, creo e ritorno un palo
                return new PaloIlluminazione(infoEntita,indirizzo);
            default:
                //nel caso in cui non ricevessi nessuna delle due di sopra, non posso fare nulla e lancio un eccezione
                throw new TipoEntitaException("segnalare o un palo o una buca");
        }
    }
}
