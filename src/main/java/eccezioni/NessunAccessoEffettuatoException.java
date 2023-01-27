package eccezioni;

public class NessunAccessoEffettuatoException extends Exception{
    //eccezione che viene chiamata quando voglio fare un operazione che puo essere fatta solo se sono online
    public NessunAccessoEffettuatoException(String message){
        super(message);
    }
}
