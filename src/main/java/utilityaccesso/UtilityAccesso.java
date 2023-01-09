package utilityaccesso;

public class UtilityAccesso {
    /*questa classe contiene 2 attributi statici che sono settati inizialmente a null
    * se l'utente effettua l' accesso con successo gli attributi di questa classe vengono settati
    * con i giusti valori presenti nel database per quell'utente, e tutte le altre classi potranno vedere queste
    * informazioni contenute nella seguente classe, e le useranno per capire se l'utete ha i permessi per entrare
    * in alcune schermate  */
    public static String nomeUtenteNelDatabase=null;
    public static String codiceUtente=null;
}
