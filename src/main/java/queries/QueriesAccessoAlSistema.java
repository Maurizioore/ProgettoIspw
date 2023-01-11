package queries;

public class QueriesAccessoAlSistema {
    /*questa e' una utility class che contiene solo metodi pubblici e statici i quali ritornano tutti una stringa
     * cioè la query che un metodo chiede di voler usare  */

    private QueriesAccessoAlSistema(){
        //NON FA NULLA PERCHE LA CLASSE DEVE FORNIRE SOLO METODI STATICI E PUBBLICI.
    }
    static final String QUERIES_VERIFICA_PRESENZA_EMAIL_E_PASSWORD="SELECT * FROM account WHERE (email=? AND password=?);";
    static final String QUERIES_VERIFICA_SE_UTENTE_ESISTE="SELECT * FROM account WHERE username=?;";
    static final String QUERIES_VERIFICA_SE_EMAIL_ESISTE="SELECT * FROM account WHERE email=?;";
    static final String QUERIES_INSERISCI_UTENTE_NEL_SISTEMA="INSERT INTO account(email,password,username) VALUES(?,?,?);";
    public static String verificaPresenzaEmailPassword(){return QUERIES_VERIFICA_PRESENZA_EMAIL_E_PASSWORD;}
    public static String verificaSeUtenteEsiste(){return QUERIES_VERIFICA_SE_UTENTE_ESISTE;}
    public static String verificaSeEmailEsiste(){return QUERIES_VERIFICA_SE_EMAIL_ESISTE;}
    public static String inserisciUtenteNelSistema(){return QUERIES_INSERISCI_UTENTE_NEL_SISTEMA;}


}
