package controllerapplicativi;

import bean.BeanLogin;
import dao.LoginDaoImpl;
import eccezioni.NonEsisteUtenteNelSistemaException;

import java.sql.SQLException;

public class ControllerApplicativoLoginAlSistema {
    //questo controller applicativo invia i dati inseriti al db e se l'utente con quelle credenziali esiste
    //allora l'utente si puo' definire loggato altrimenti ci sara' un ritorno di errore
    private final String email;
    private final String password;
    //costruttore a cui il controller grafico passa i bean
    public ControllerApplicativoLoginAlSistema(BeanLogin beanLogin) throws SQLException, NonEsisteUtenteNelSistemaException {
        this.email=beanLogin.getEmail();
        this.password=beanLogin.getPassword();
        inviaSegnalazione();
    }
    public void inviaSegnalazione() throws SQLException, NonEsisteUtenteNelSistemaException {
        //devo aprire una connessione e far inviare al dao del login i dati
        LoginDaoImpl loginDao = new LoginDaoImpl();
        //questo metodo ritorna true se l'utente e' registrato nel sistema, false altrimenti
        if (loginDao.verificaAccountNelSistema(email, password)) {
            //l'utente è registrato nel sistema, questo viene comunicato al controller applicativo, il quale indirizza
            //l'utente alla home, altrimenti viene detto che quell'account non esiste
            //qui l'idea sarebbe di prendere il nome dell'utente e metterlo su schermo e inoltre settare una variabile
            //globale nel controller applicativo che mostra il codice dell'utente e questa farà capire agli altri
            //controller che l'utente che sta interagendo con il sistema e' effettivamente un utente registrato
            //volendo posso mettere questa variabile codice in una variabile statica di una classe e setterla a null
            //poi quando l'utente fa l'accesso questa viene settata con il codice dell'utente
            return ;
        }
        //se l'account non esiste nel sistema( quindi l'if) non viene svolto, lancio una eccezione
        throw new NonEsisteUtenteNelSistemaException("non esiste nessun utente con queste\ncredenziali");
    }

}
