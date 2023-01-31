package controllergraficicommandlineinterface;

import bean.BeanLogin;
import cli.PaginaHome;
import controllerapplicativi.ControllerApplicativoLoginAlSistema;
import eccezioni.ErroreLetturaPasswordException;
import eccezioni.NonEsisteUtenteNelSistemaException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class ControllerGraficoInviaDatiAccessoAlSistemaCli {
    private PaginaHome paginaHome=new PaginaHome();
    private String email;
    private String password;
    private BeanLogin beanAccessoUtente;
    public ControllerGraficoInviaDatiAccessoAlSistemaCli(String email, String password){
        this.email=email;
        this.password=password;
    }
    public void inviaDatiAlBean(){
        beanAccessoUtente = new BeanLogin(email, password);
        //svolgo prima i controlli sulla email inserita dall'utente, verifico cioè se è sintatticamente corretta
        String controlliSintatticiEmail = beanAccessoUtente.svolgiControlli();
        //se l'email è sintatticamente corretta vado avanti altrimenti counico l'errore all'utente
        if (controlliSintatticiEmail == null) {
            //mando il bean al controller applicativo
            try {
                new ControllerApplicativoLoginAlSistema(beanAccessoUtente);
                // se non si e' verificata nessuna eccezione vuol dire che l'accesso e' stato effettuato con successo
                System.out.println("accesso effettuato, premi qualsiasi tasto per tornare alla home");
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));
                if(bufferedReader.readLine().length()>=1){
                    tornaAllaHome();
                }
            }catch(SQLException | NonEsisteUtenteNelSistemaException | ErroreLetturaPasswordException| IOException e){
                System.out.println(e.getMessage());
            }
        }else{
            System.out.println(controlliSintatticiEmail);
        }

    }
    private void tornaAllaHome() throws IOException {
        paginaHome.displayHomepage();
    }
}
