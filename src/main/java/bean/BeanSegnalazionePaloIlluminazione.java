package bean;


import eccezioni.LunghezzaInputException;

public class BeanSegnalazionePaloIlluminazione {
    //note:
    //(UNO) assumo che un numero seriale possa contenere numeri e lettere e mai solo numeri o solo lettere e che abbia
    //   una lunghezza di 12 caratteri fissata
    //(DUE) assumo che un indirizzo inizi con via oppure contrada
    /*il bean dopo aver fatto i controlli sintattici deve comunicare con il controller applicativo, sara' lui
    * che verificherà che il palo stesso (e non la sua sintassi) e anche l'indirizzo siano corretti.
    * Implementerò questa verifica usando due contenitori, uno per l'insieme dei pali e uno per l'insieme degli indirizzi
    * se entrambi sono corretti allora il controller applicativo creerà un oggetto di tipo palo con uno stato iniziale
    * (da riparare), altrimenti verrà generata un eccezione che verrà presa dal bean e comunicata alla view */


    //variabile che conterrà il numero seriale del palo dell'illuminazione passato dall'utente
    private final String  numeroSerialePalo;

    //lunghezza fissata per i numeri seriali che possiedono i pali dell'illuminazione
    private static final int LUNGHEZZANUMEROSERIALE=12;

    //variabile che conterrà l'indirizzo passato dall'utente
    private final String indirizzo;

    public BeanSegnalazionePaloIlluminazione(String numeroSerialePalo, String indirizzo) {
        this.numeroSerialePalo = numeroSerialePalo;
        this.indirizzo = indirizzo;
    }
    public String getNumeroSerialePalo() {
        return numeroSerialePalo;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void controllaInputPalo()throws LunghezzaInputException{
        if (numeroSerialePalo.length() != LUNGHEZZANUMEROSERIALE) {
            throw new LunghezzaInputException("\nLa lunghezza del numero seriale non e' corretta");
        }
    }
}
