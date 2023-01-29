package entita;

import factory.TypeEntita;

public class BucaStradale extends EntitaStradale{
    public final String profondita;
    public final String indirizzo;

    public BucaStradale(String profondita,String indirizzo){
        this.profondita=profondita;
        this.indirizzo=indirizzo;
        this.typeEntita= TypeEntita.BUCASTRADALE;
    }

    @Override
    public String infoEntita() {
        return profondita;
    }
    @Override
    public String getIndirizzo() {
        return this.indirizzo;
    }
}
