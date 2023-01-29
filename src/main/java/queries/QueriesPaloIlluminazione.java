package queries;

public class QueriesPaloIlluminazione {
    //questa classe svolgerà il ruolo di utility class, avrà solo metodi statici che restituiscono stringhe
    //che altro non sono che le query che posso fare sul mio oggetto Palo Illuminazione
    //nota nelle query il termine 'palo' e' il nome della relazione presente nel database "database-app-ispw"
    //quetse sono dei prepare statment, i valori nei ? verranno settati nella classe che vuole svolgete la query
    static final String QUERIES_PRENDI_PALO="SELECT * FROM palo where numeroSeriale=?";
    static final String QUERIES_SALVA_PALO="INSERT INTO palo(numeroSeriale,indirizzo) VALUES(?,?);";
    static final String QUERIES_RIMUOVI_PALO="DELETE  FROM palo where (numeroSeriale=? AND indirizzo=?);";
    static final String QUERIES_RESTITUISCI_PALO="SELECT * FROM palo";
    static final String QUERIES_CAMBIA_STATO_PALO="UPDATE palo SET stato = ? WHERE (numeroSeriale=? AND indirizzo=?);";
    static final String QUERIES_CERCA_PALO="SELECT * FROM palo WHERE (numeroSeriale=? AND indirizzo=?);";
    static final String QUERIES_SALVA_PALO_AD_UN_UTENTE_DEL_SISTEMA="INSERT INTO palo(numeroSeriale,indirizzo,codiceUtente) VALUES(?,?,?);";
    static final String QUERIES_MOSTRA_SEGNALAZIONI_EFFETTUATE="SELECT DISTINCT numeroSeriale,indirizzo,stato FROM palo,account WHERE (palo.codiceUtente=? AND problemaRisolto=false);";
    //provala questa delle completate prima
    static final String QUERIES_MOSTRA_SEGNALAZIONI_COMPLETATE="SELECT DISTINCT numeroSeriale,indirizzo FROM palo,account WHERE (palo.codiceUtente=? AND problemaRisolto!=false);";
    private QueriesPaloIlluminazione(){
        //NON FA NULLA PERCHE LA CLASSE DEVE FORNIRE SOLO METODI STATICI E PUBBLICI
    }
    public static String queriesPrendiPalo(){
        return QUERIES_PRENDI_PALO;
    }
    public static String queriesSalvaPalo(){return QUERIES_SALVA_PALO;}
    public static String queriesRimuoviPalo(){return QUERIES_RIMUOVI_PALO;}
    public static String  restituisciPalo(){return QUERIES_RESTITUISCI_PALO;}
    public static String cambiaStatoPalo(){return QUERIES_CAMBIA_STATO_PALO;}
    public static String cercaPaloIlluminazione(){return QUERIES_CERCA_PALO;}

    //queries che salva un palo se l'utente si e' loggato
    public static String queriesSalvaPaloAdUnUtenteDelSistema(){return QUERIES_SALVA_PALO_AD_UN_UTENTE_DEL_SISTEMA;}

    //queries per vedere i pali che gli utenti loggati hanno segnalato e vogliono controllare lo stato della segnalazione
    public static String queriesMostraSegnalazioniEffettuate(){
        return QUERIES_MOSTRA_SEGNALAZIONI_EFFETTUATE;
    }
    public static String queriesMostraSegnalazioniCompletate(){return QUERIES_MOSTRA_SEGNALAZIONI_COMPLETATE;}


}
