package entita;

import factory.TypeEntita;

public abstract class EntitaStradale {
    //tutte le entità stradali che vengono segnalate possiedono :
    //un indirizzo che indica dove si trovano
    //e una stato iniziale che indica che la segnalazione e' stata inviata
    protected String stato="in riparazione";
    protected TypeEntita typeEntita;

    public abstract String getIndirizzo();
    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }
    public  abstract String infoEntita();

    public TypeEntita getTypeEntita() {
        return typeEntita;
    }

    public void setTypeEntita(TypeEntita typeEntita) {
        this.typeEntita = typeEntita;
    }
}
