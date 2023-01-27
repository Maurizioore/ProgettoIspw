package bean;

import eccezioni.LunghezzaInputException;
import factory.TypeEntita;

import java.lang.reflect.Type;

public class BeanSegnalaEntita {
    //questo e' il bean che passerà i dati inseriti dall'utente al controller applicativo che invia la registrazioni

    private String indirizzo;
    private String infoEntita;
    //private String tipoEntitaSegnalata;
    private TypeEntita tipo;
    private static final int LUNGHEZZANUMEROSERIALE=12;
    public BeanSegnalaEntita(String infoEntita, String indirizzo, TypeEntita tipoEntitaSegnalata){
        this.indirizzo=indirizzo;
        this.infoEntita=infoEntita;
        this.tipo=tipoEntitaSegnalata;
    }
    public void controllaInputPalo()throws LunghezzaInputException {
        if (infoEntita.length() != LUNGHEZZANUMEROSERIALE) {
            throw new LunghezzaInputException("\nLa lunghezza del numero seriale non e' corretta");
        }
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getInfoEntita() {
        return infoEntita;
    }

    public void setInfoEntita(String infoEntita) {
        this.infoEntita = infoEntita;
    }

    //public String getTipoEntitaSegnalata() {
    //    return tipoEntitaSegnalata;
    //}
    //
    //public void setTipoEntitaSegnalata(String tipoEntitaSegnalata) {
    //    this.tipoEntitaSegnalata = tipoEntitaSegnalata;
    //}
    public TypeEntita getTipoEntitaSegnalata(){
        return tipo;
    }
    public void setTipoEntitaSegnalata(TypeEntita tipoEntitaSegnalata) {
        this.tipo = tipoEntitaSegnalata;
}   }
