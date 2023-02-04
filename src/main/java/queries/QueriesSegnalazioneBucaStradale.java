package queries;

public class QueriesSegnalazioneBucaStradale {

    //costruiamo le buche in modo che solo gli utenti registrati al sistema possono segnalarle
    //operazione che puo fare solo l'admin
    private QueriesSegnalazioneBucaStradale(){
        //non viene chiamato da nessuno, questa classe e' una utity alla fine
    }
    static final String QUERIES_VEDI_BUCHE_SEGNALATE="SELECT * FROM buca";
    //operazione che possono fare gli utenti
    static final String QUERIES_SALVA_BUCA="INSERT INTO buca(indirizzo,profondità,codiceUtente) VALUES(?,?,?);";
    //operazione che puo fare solo l'admin dopo che il problema e' stato risolto
    static final String QUERIES_RIMUOVI_BUCA="DELETE  FROM buca where (indirizzo=? AND codice_utente=?);";
    static final String QUERIES_CAMBIA_STATO_BUCA="UPDATE buca SET stato = ? WHERE (codiceUtente=? AND indirizzo=?);";
    //queste 2 che mostrano le segnalazioni falle girare prima su mysql rivedila
    static final String QUERIES_MOSTRA_SEGNALAZIONI_EFFETTUATE="SELECT DISTINCT indirizzo,stato,profondità FROM buca,account WHERE (buca.codiceUtente=? AND problemaRisolto=false);";
    //provala questa delle completate prima
    static final String QUERIES_MOSTRA_SEGNALAZIONI_COMPLETATE="SELECT DISTINCT profondità,indirizzo,stato FROM buca,account WHERE (buca.codiceUtente=? AND problemaRisolto!=false);";


    public static String queriesVediBucheSegnalate(){
        return QUERIES_VEDI_BUCHE_SEGNALATE;
    }
    public static String queriesSalvaBuca(){return QUERIES_SALVA_BUCA;}
    public static String queriesRimuoviBuca(){return QUERIES_RIMUOVI_BUCA;}
    public static String queriesCambiaStatoBuca(){return QUERIES_CAMBIA_STATO_BUCA;}
   //queries per vedere le buche che gli utenti loggati hanno segnalato
    public static String queriesMostraSegnalazioniEffettuate(){
        return QUERIES_MOSTRA_SEGNALAZIONI_EFFETTUATE;
    }
    //queries per mostrare le buche che sono state riparate
    public static String queriesMostraSegnalazioniCompletate(){return QUERIES_MOSTRA_SEGNALAZIONI_COMPLETATE;}
}
