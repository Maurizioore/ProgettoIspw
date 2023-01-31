package bean;

import eccezioni.DoppiaChiocciolaException;
import eccezioni.DoppiaVirgolaException;
import eccezioni.TerminatoreEmailException;


public class BeanLogin {

    /*questo bean riceve i dati quali email e password dall'utente e li invia al controller applicativo che gestisce
    l'accesso degli utenti al sistema*/
    private String email;
    private String password;
    public BeanLogin(String email,String password) {
        this.email=email.toLowerCase();
        this.password=password;
    }
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    public String svolgiControlli(){
        try {
            BeanVerifica.verificaSintassiEmail(email);
        }catch(DoppiaChiocciolaException|DoppiaVirgolaException |TerminatoreEmailException e){
            return e.getMessage();
        }
        return null;
    }
}
