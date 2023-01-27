package entita;

public class BucaStradale extends EntitaStradale{
    public final String profondita;

    public BucaStradale(String profondita,String indirizzo){
        this.profondita=profondita;
        this.indirizzo=indirizzo;
    }

    @Override
    public String infoEntita() {
        return profondita;
    }
}
