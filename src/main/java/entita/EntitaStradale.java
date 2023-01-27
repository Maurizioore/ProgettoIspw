package entita;

public abstract class EntitaStradale {
    //tutte le entità stradali che vengono segnalate possiedono :
    //un indirizzo che indica dove si trovano
    //e una stato iniziale che indica che la segnalazione e' stata inviata
    public String indirizzo;
    public String stato="in riparazione";

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }
    public  abstract String infoEntita();
}
