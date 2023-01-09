package bean;

import eccezioni.DoppiaChiocciolaException;
import eccezioni.DoppiaVirgolaException;
import eccezioni.TerminatoreEmailException;

public class BeanRegistrazione{
    private String email;
    private String password;
    private String username;
    public BeanRegistrazione(String email,String password,String  username){
        this.email=email;
        this.password=password;
        this.username=username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
    //questo secondo me lo conta come duplicazione
    public String svolgiControlli(){
        try {
            BeanVerifica.verificaSintassiEmail(email);
        }catch(DoppiaChiocciolaException | DoppiaVirgolaException | TerminatoreEmailException e){
            return e.getMessage();
        }
        return null;
    }

    //public void verificaSintassiEmail() throws DoppiaChiocciolaException,DoppiaVirgolaException, TerminatoreEmailException{
    //    int contatore = 0;
    //    int contatore2 = 0;
    //    contatore = email.indexOf('@');
    //    contatore2 = email.indexOf('@', contatore + 1);
    //    if (contatore2 != -1) {
    //        //bisogna lanciare un eccezione
    //        throw new DoppiaChiocciolaException("l'email inserita deve\ncontenere una sola '@' ");
    //    }
    //    if (email.indexOf(',') != -1) {
    //        throw new DoppiaVirgolaException("spiacente l'email non può\ncontente un carattere ','");
    //    }
    //    if(!(email.endsWith(".com") || email.endsWith(".it") || email.endsWith(".it"))){
    //        throw new TerminatoreEmailException("l'email deve terminare con\n.com o .it o .live");
    //    }
    //}
}
