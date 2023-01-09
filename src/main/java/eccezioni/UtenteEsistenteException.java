package eccezioni;

public class UtenteEsistenteException extends Exception{
    public UtenteEsistenteException(String message){
        super(message);
    }
}
