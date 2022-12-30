package entita;

public class PaloIlluminazione {
    //questo e' il model deve comunicare con il db, se tutto va a buon fine farà sapere al controller applicativo
    //che la segnalazione ha riscontrato successo, altrimenti no e lancierà un eccezione
    private static int numeroSegnalazioniRicevute;
    private int numeroSegnalazionePalo;
    private final String numeroSerialePalo;
    private String statoRiparazione;
    private final String indirizzo;

    public PaloIlluminazione(String numeroSerialePalo,String statoRiparazione,String indirizzo){
        this.numeroSerialePalo=numeroSerialePalo;
        this.indirizzo=indirizzo;
        numeroSegnalazionePalo=numeroSegnalazioniRicevute++;
        CambiaStato(statoRiparazione);
    }
    public int getNumeroSegnalazioneAttuale(){
        return numeroSegnalazionePalo;
    }
    //metodo per cambiare lo stato del palo in tempo reale per farlo passare da in riparazione a riparato
    public void CambiaStato(String nuovoStato) {
        if (nuovoStato.equals("in corso di riparazione") || nuovoStato.equals("riparato")) {
            this.statoRiparazione = nuovoStato;
            System.out.println("palo in riparazione: sono una sout presente nell'entità PaloIlluminazione");
        } else {
            //lo stato non sara' valido e verra' fatto qualcosa, per ora metto l'uscita dal sistema
            System.exit(-1);
        }
    }

}
