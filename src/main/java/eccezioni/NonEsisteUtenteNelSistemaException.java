package eccezioni;

public class NonEsisteUtenteNelSistemaException extends Exception{
    public NonEsisteUtenteNelSistemaException(String message){
        super(message);
    }
}
