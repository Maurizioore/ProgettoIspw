package controllerapplicativi;

import bean.BeanRegistrazione;
import dao.RegistrazioneDaoImpl;
import eccezioni.UtenteEsistenteException;

import java.sql.SQLException;

public class ControllerApplicativoRegistrazioneAlSistema {
    private String email;
    private String password;
    private String username;

    public ControllerApplicativoRegistrazioneAlSistema(BeanRegistrazione bean) throws SQLException, UtenteEsistenteException {
        email= bean.getEmail();
        username=bean.getUsername();
        password= bean.getPassword();
        registraUtente();
    }
    private void registraUtente() throws SQLException, UtenteEsistenteException {
        //devo usare un dao per prendere la connessione e far registrare l'utente nel sistema
        RegistrazioneDaoImpl registrazioneDao=new RegistrazioneDaoImpl();
        registrazioneDao.registraUtente(username,email,password);
    }
}
