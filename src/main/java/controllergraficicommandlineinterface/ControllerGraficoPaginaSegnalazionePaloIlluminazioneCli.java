package controllergraficicommandlineinterface;

import bean.BeanSegnalaEntita;
import cli.PaginaHome;
import controllerapplicativi.ControllerApplicativoSegnalazioneEntita;
import eccezioni.*;

import factory.TypeEntita;
import factory.TypeOfPersistence;
import utility.Printer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class ControllerGraficoPaginaSegnalazionePaloIlluminazioneCli {
    private PaginaHome paginaHome=new PaginaHome();
    private String numeroSeriale;
    private String indirizzo;

    private BeanSegnalaEntita beanVerificaDati;

    private TypeEntita typeEntita = TypeEntita.PALOILLUMINAZIONE;

    private TypeOfPersistence typeOfPersistence;

    public ControllerGraficoPaginaSegnalazionePaloIlluminazioneCli(String numeroSeriale,String indirizzo,int tipoPersistenza){
        this.numeroSeriale=numeroSeriale;
        this.indirizzo=indirizzo;
        if(tipoPersistenza==1){
            typeOfPersistence=TypeOfPersistence.JDBC;
        }else{
            typeOfPersistence=TypeOfPersistence.FILESYSTEM;
        }
    }
    public void inviaDatiAlBean() throws IOException {
        //qui invio i dati al bean
        beanVerificaDati =  new BeanSegnalaEntita(numeroSeriale,indirizzo, typeEntita, typeOfPersistence);
        try {
            beanVerificaDati.controllaInputPalo();
            new ControllerApplicativoSegnalazioneEntita(beanVerificaDati);
            //se non c'e' stata nessuna eccezione vuol dire che la segnalazione e' avvenuta con successo
            //lo comunico all'utente e blocco i pulsanti per non far inviare la stessa segnalazione
            //in caso dovesse premere per sbaglio di nuovo il pulsante invia
            Printer.print("segnalazione avvenuta con successo\ntorna alla home =)\npremere qualsiasi tasto per tornare alla home: ");
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));
            if(bufferedReader.readLine().length()>=1){
                tornaAllaHome();
            }
        }catch(LunghezzaInputException | TipoEntitaException | SegnalazioneGiaAvvenutaException | NessunAccessoEffettuatoException | SQLException | ErroreLetturaPasswordException |IOException e){
            Printer.error(e.getMessage());
            tornaAllaHome();
        }
    }
    private void tornaAllaHome() throws IOException {
        paginaHome.displayHomepage();
    }
}
