package dao;

import eccezioni.UtenteEsistenteException;
import queries.QueriesAccessoAlSistema;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegistrazioneDaoImpl implements RegistrazioneDao{
    //questa come tutte le altri classi dao comunica con il db ed esegue l'operazione di registrazione
    //prima di fare la registrazione verifica se esiste già un utente con quelle credenziali e se no lo registra
    private void verificaConnessione() throws SQLException {
        if(connection==null){
            new RegistrazioneDaoImpl();
        }
    }
    private Connection connection=null;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    public RegistrazioneDaoImpl() throws SQLException {
        //prendo o apro una connessione se non esiste
        connection=SingletonConnessione.getInstance();
    }
    //una volta aperta la connessione posso eseguire le operazioni
    @Override
    public void registraUtente(String username, String email, String password) throws SQLException, UtenteEsistenteException {
        //prima di eseguire ogni operazione controllo che la connessione sia aperta
        verificaConnessione();
        //ora devo registrare l'utente con i dati passati, devo prima vedere se esiste qualche utente con quelle credenziali
        if(verificaEsistenzaUtente(username,email)){
            //non esiste un utente nel sistema posso quindi registrarlo
            preparedStatement=connection.prepareStatement(QueriesAccessoAlSistema.inserisciUtenteNelSistema());
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);
            preparedStatement.setString(3,username);
            preparedStatement.executeUpdate();
        }else {
            throw new UtenteEsistenteException("la username o l'email inserite sono associate\nad un altro account");
        }
    }
    @Override
    public boolean verificaEsistenzaUtente(String username, String email) throws UtenteEsistenteException, SQLException {
        preparedStatement = connection.prepareStatement(QueriesAccessoAlSistema.verificaSeUtenteEsiste());
        preparedStatement.setString(1, username);
        resultSet = preparedStatement.executeQuery();
        if (resultSet.isBeforeFirst()) {
            //esiste un utente che ha usato quell'username
            return false;
        }else{
            preparedStatement=connection.prepareStatement(QueriesAccessoAlSistema.verificaSeEmailEsiste());
            preparedStatement.setString(1,email);
            resultSet=preparedStatement.executeQuery();
            if(resultSet.isBeforeFirst()){
                //esiste un utente che usa quell'email
                return false;
            }
            return true;
        }

    }
}
