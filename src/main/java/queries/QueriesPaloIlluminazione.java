package queries;

public class QueriesPaloIlluminazione {
    //questa classe svolgerà il ruolo di utility class, avrà solo metodi statici che restituiscono stringhe
    //che altro non sono che le query che posso fare sul mio oggetto Palo Illuminazione
    //nota nelle query il termine 'palo' e' il nome della relazione presente nel database "database-app-ispw"
    //quetse sono dei prepare statment, i valori nei ? verranno settati nella classe che vuole svolgete la query
    public static String queriesPrendiPalo(){
        return "SELECT * FROM palo where numeroSeriale=?";
    }
    public static String queriesSalvaPalo(){return "INSERT INTO palo(numeroSeriale,indirizzo) VALUES(?,?);";}
    public static String queriesRimuoviPalo(){return "DELETE  FROM palo where (numeroSeriale=? AND indirizzo=?); ";}
    public static String  restituisciPalo(){return "SELECT * FROM palo";}
    public static String cambiaStatoPalo(){return "UPDATE palo SET stato = ? WHERE (numeroSeriale=? AND indirizzo=?);";}
    public static String cercaPaloIlluminazione(){return " SELECT * FROM palo WHERE (numeroSeriale=? AND indirizzo=?);";}


}
