package dao;

import eccezioni.ErroreLetturaPasswordException;
import eccezioni.UtenteEsistenteException;
import java.sql.SQLException;

public interface RegistrazioneDao {
    //qui metto le operazioni che mi aspetto di fare con la registrazione, ossia registrare un utente nel sistema
    boolean registraUtente(String username, String email, String password) throws SQLException, UtenteEsistenteException;
    boolean verificaEsistenzaUtente(String username, String email) throws UtenteEsistenteException, SQLException, ErroreLetturaPasswordException;
}
