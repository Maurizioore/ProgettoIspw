package eccezioni;

public class NonEsistonoSegnalazioniException extends Exception{

    public NonEsistonoSegnalazioniException(String message){
        super(message);
    }
}
