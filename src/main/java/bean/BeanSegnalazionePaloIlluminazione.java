package bean;

import eccezioni.LunghezzaInputException;
import eccezioni.NonEsisteIndirizzoException;
import eccezioni.NonEsisteNumeroSerialeException;
import controllerapplicativi.ControllerApplicativoSegnalazionePaloIlluminazione;

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
    private static final int lunghezzaNumeroSeriale=12;

    //variabile che conterrà l'indirizzo passato dall'utente
    private final String indirizzo;

    /*costruttore che verrà chiamato se l'utente ha inserito realmente qualcosa in ingresso nei campi d' input, se avrà
    * lasciato vuoto uno dei due campi il controller grafico lo bloccherà senza generare nessun bean*/

    public BeanSegnalazionePaloIlluminazione(String numeroSerialePalo, String indirizzo) {
        this.numeroSerialePalo = numeroSerialePalo;
        this.indirizzo = indirizzo;
    }

    /*il bean ricorda che fa solo il getter e il setter, non si preoccupa di vedere se quello che è passato
     effettivamente giusto, lui puo' volendo fare controlli sintattici, (ed è quello che gli facciamo fare ora), e
     se non ci sono errori deve comunicare con il controller applicativo */

    public String svolgiControlli(){
        try{
            controllaInputPalo();
            //se non c'e' stata eccezione vuol dire che la lunghezza del numero seriale è corretta
            /*passo il bean quindi passa i dati in input al controller applicativo, il quale farà le verifiche anche sulla
            * reale correttezza dei dati e in caso ci fossero errori, lancerà delle eccezioni che verranno gestite qui nel bean */
            new ControllerApplicativoSegnalazionePaloIlluminazione(numeroSerialePalo,indirizzo);
            //se ritorno null vuol dire che tutto è andato a buon fine e non c'e' stata nessuna eccezione
            return null;
        }catch(LunghezzaInputException | NonEsisteNumeroSerialeException | NonEsisteIndirizzoException e){
            //qui gestisco le eccezioni che si possono creare con le chiamate nel blocco try
            return e.toString();
        }
    }
    /*questo metodo non fara' altro che controllare che la lunghezza passata dall'utente sia uguale a quella che mi
    * aspetto di avere, in caso contrario viene lanciata una eccezione di tipo LunghezzaInputException presente nel
    * package eccezioni e viene ritornata un stringa che segnalerà l'errore all'utente */
    private void controllaInputPalo()throws LunghezzaInputException{
        if (numeroSerialePalo.length() != lunghezzaNumeroSeriale) {
            throw new LunghezzaInputException("\nLa lunghezza del numero seriale non e' corretta");
        }
    }
}
