package entita;

import factory.TypeEntita;

public class PaloIlluminazione extends EntitaStradale{

    //questo è il model deve comunicare con il db, se tutto va a buon fine farà sapere al controller applicativo
    //che la segnalazione ha riscontrato successo, altrimenti no e lancerà un eccezione
    /*idea un numeroSegnalazionePalo volendo può essere una primary key nel mio database, in modo che identifico
    * la mia segnalazione */
    public final String numeroSerialePalo;
    public final String indirizzo;
    public PaloIlluminazione(String numeroSerialePalo,String indirizzo){
        this.numeroSerialePalo=numeroSerialePalo;
        this.indirizzo=indirizzo;
        this.typeEntita= TypeEntita.PALOILLUMINAZIONE;
    }

    @Override
    public String infoEntita() {
        return numeroSerialePalo;
    }
    @Override
    public String getIndirizzo() {
        return this.indirizzo;
    }
}
