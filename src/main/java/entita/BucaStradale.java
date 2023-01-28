package entita;

import factory.TypeEntita;

public class BucaStradale extends EntitaStradale{
    public final String profondita;

    public BucaStradale(String profondita,String indirizzo){
        this.profondita=profondita;
        this.indirizzo=indirizzo;
        this.typeEntita= TypeEntita.type_buca_stradale;
    }

    @Override
    public String infoEntita() {
        return profondita;
    }
}
