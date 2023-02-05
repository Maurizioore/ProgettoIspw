package controllerapplicativi;

import bean.BeanRegistrazione;
import dao.RegistrazioneDaoImpl;
import eccezioni.ErroreLetturaPasswordException;
import eccezioni.UtenteEsistenteException;

import java.sql.SQLException;

public class ControllerApplicativoRegistrazioneAlSistema {
    private String email;
    private String password;
    private String username;

    public ControllerApplicativoRegistrazioneAlSistema(BeanRegistrazione bean) throws SQLException, UtenteEsistenteException, ErroreLetturaPasswordException {
        email= bean.getEmail();
        username=bean.getUsername();
        password= bean.getPassword();
        registraUtente();
    }
    private void registraUtente() throws SQLException, UtenteEsistenteException, ErroreLetturaPasswordException {
        //devo usare un dao per prendere la connessione e far registrare l'utente nel sistema
        RegistrazioneDaoImpl registrazioneDao=new RegistrazioneDaoImpl();
        if(!registrazioneDao.registraUtente(username, email, password)){
            //la registrazione non è avvenuta, esiste un utente che usa già quelle credenziali, lancio un eccezioni che
            //comunica all'utente che non puo' registrarsi con quelle credenziali
            throw new UtenteEsistenteException("la username o l'email inserite sono associate\nad un altro account");
        }
    }
}
