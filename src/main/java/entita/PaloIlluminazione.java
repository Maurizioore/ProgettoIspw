package entita;

public class PaloIlluminazione {

    //questo e' il model deve comunicare con il db, se tutto va a buon fine farà sapere al controller applicativo
    //che la segnalazione ha riscontrato successo, altrimenti no e lancierà un eccezione
    //private static int numeroSegnalazioniRicevute;
    /*idea un numeroSegnalazionePalo volendo può essere una primary key nel mio database, in modo che identifico
    * la mia segnalazione */
    //private final int numeroSegnalazionePalo;
    public final String numeroSerialePalo;
    public final String indirizzo;

    public PaloIlluminazione(String numeroSerialePalo,String indirizzo){
        this.numeroSerialePalo=numeroSerialePalo;
        this.indirizzo=indirizzo;
        //numeroSegnalazionePalo=++numeroSegnalazioniRicevute;
        //System.out.println(numeroSegnalazionePalo);
        //cambiaStato(statoRiparazione);
    }
    //public int getNumeroSegnalazioneAttuale(){
    //    return numeroSegnalazionePalo;
    //}
    //metodo per cambiare lo stato del palo in tempo reale per farlo passare da in riparazione a riparato
    //public void cambiaStato(String nuovoStato) {
    //    if (nuovoStato.equals("in corso di riparazione") || nuovoStato.equals("riparato")) {
    //        this.statoRiparazione = nuovoStato;
    //        System.out.println("palo in riparazione: sono una sout presente nell'entità PaloIlluminazione");
    //    } else {
    //        //lo stato non sara' valido e verra' fatto qualcosa, per ora metto l'uscita dal sistema
    //        System.exit(-1);
    //    }
    //}

}
