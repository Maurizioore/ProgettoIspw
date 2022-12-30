package eccezioni;

public class NonEsisteIndirizzoException extends Exception{
    public NonEsisteIndirizzoException(String message){
        super(message);
    }
}
