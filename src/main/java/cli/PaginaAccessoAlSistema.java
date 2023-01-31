package cli;

import controllergraficicommandlineinterface.ControllerGraficoInviaDatiAccessoAlSistemaCli;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PaginaAccessoAlSistema {
    private BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));
    private String email;
    private String password;

    public void mostraPaginaAccesso() throws IOException {
        System.out.println("--------------------------Pagina log in----------------------------\n" +
                "digita la tua email ( digita esc per tornare indietro):");
        this.email=bufferedReader.readLine();
        System.out.println("inserisci la tua password ( se hai digitato esc per tornare indietro, digita qualsiasi lettera ora): ");
        this.password=bufferedReader.readLine();
        //controlla se l'utente e' voluto tornare indietro
        if(verificaInputUscita(email)){
            //l'utente vuole tornare alla home
            tornaAllaHomePage();
        }
        if (email.equals("") || password.equals("")) {
            System.out.println("la prossima volta inserisci una email e una password");
            tornaAllaHomePage();
        }
        //mando questi dati al controller grafico il quale li manda al bean
        ControllerGraficoInviaDatiAccessoAlSistemaCli controllerGraficoInviaDatiAccessoAlSistemaCli=new ControllerGraficoInviaDatiAccessoAlSistemaCli(this.email,this.password);
        controllerGraficoInviaDatiAccessoAlSistemaCli.inviaDatiAlBean();


    }
    private boolean verificaInputUscita(String email){
        return email.equalsIgnoreCase("esc");

    }
    private void tornaAllaHomePage() throws IOException {
        PaginaHome paginaHome=new PaginaHome();
        paginaHome.displayHomepage();
    }
}
