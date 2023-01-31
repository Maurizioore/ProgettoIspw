package dao;

import eccezioni.ErroreLetturaPasswordException;
import entita.Account;
import queries.QueriesAccessoAlSistema;
import utilityaccesso.UtilityAccesso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDaoImpl implements LoginDao{
    //questa classe altro non fa che implementare i metodi che mi aspetto di fare con il login e aprire una connessione
    //nel caso questa non sia già stata aperta
    //ovviamente la classe dao come al solito si occupa di aprire una connessione ed eseguire le operazioni che
    //mi aspetto di avere per quel database,in questo caso voglio solo verificare che siano presenti le
    //credenziali di accesso nel sistema per il mio utente
    private Connection connection=null;
    private PreparedStatement preparedStatement=null;
    private ResultSet resultSet=null;
    private Account account;
    public LoginDaoImpl() throws SQLException, ErroreLetturaPasswordException {
        connection=SingletonConnessione.getInstance();
        account=Account.getInitialAccount();
    }

    public boolean verificaAccountNelSistema(String email,String password) throws SQLException {
        //arrivati a questo punto devo fare una interrogazione al mio database sql, precisamente devo fare un
        //interrogazione alla tabella che contiene email e password chiamata "account" e vedere se esiste una tupla con
        //quelle credenziali
        //creo quindi uno statement e gli passo un interrogazione presente QueriesAccessoAlSistema
        preparedStatement=connection.prepareStatement(QueriesAccessoAlSistema.verificaPresenzaEmailPassword());
        preparedStatement.setString(1,email);
        preparedStatement.setString(2,password);
        resultSet=preparedStatement.executeQuery();
        //se l'utente e' registrato nel sistema prendo il suo nome utente e il suo codice e lo salvo nelle 2 variabili
        //globali della classe che tiene solo queste informazioni e che fa capire agli altri controller ( se queste 2
        //informazioni sono diverse da null) che l'utente che sta eseguendo e' effettivamente un utente del sistema
        //registrato e loggato
        //isBeforeFirst ritorna true se il result set contiene qualcosa, false altrimenti
        if(resultSet.isBeforeFirst()){
            resultSet.next();
            //in questo caso potrei dire che è li che ha un legame con account e modifica lo stato dei suoi attributi
            account.setCredenziali(resultSet.getString("username"),Integer.toString(resultSet.getInt("codiceUtente")));
            account.passaOnline();
            UtilityAccesso.setNomeUtenteNelDatabase(resultSet.getString("username"));
            //converto il tipo intero di codice del database in un tipo stringa poiche codiceUtente in UtilityAccesso
            //è di tipo stringa
            UtilityAccesso.setCodiceUtente(Integer.toString(resultSet.getInt("codiceUtente")));
            //System.out.println("sono una sout presente in loginDaoImpl, codice utente settato a: "+UtilityAccesso.getCodiceUtente());
            //System.out.println("sono una sout in loginDaoImpl nome utente settato a: "+UtilityAccesso.getNomeUtenteNelDatabase());
            //l'utente esiste nel database, ha fatto l'accesso, e' quindi online
            //UtilityAccesso.getAccount().passaOnline();
            //l'utente è stato trovato nel database, torno true
            return true;
        }
        //quelle credenziali non sono presenti nel sistema, quindi ritorno false
        return false;
    }
}
