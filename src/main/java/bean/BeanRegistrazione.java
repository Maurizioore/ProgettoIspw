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

}
