package controllergraficicommandlineinterface;

import bean.BeanSegnalaEntita;
import cli.PaginaHome;
import controllerapplicativi.ControllerApplicativoSegnalazioneEntita;
import eccezioni.*;
import factory.TypeEntita;
import factory.TypeOfPersistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class ControllerGraficoPagineSegnalazioneBucaStradaleCli {
    private PaginaHome paginaHome=new PaginaHome();
    private String indirizzo;
    private int profondita;

    private BeanSegnalaEntita beanVerificaDati;
    private TypeEntita typeEntita=TypeEntita.BUCASTRADALE;
    private TypeOfPersistence typeOfPersistence;


    public ControllerGraficoPagineSegnalazioneBucaStradaleCli(String indirizzo, int profondita,int typeOfPersistence){
        this.indirizzo=indirizzo;
        this.profondita=profondita;
        if(typeOfPersistence==1){
            this.typeOfPersistence=TypeOfPersistence.JDBC;
        }else{
            this.typeOfPersistence=TypeOfPersistence.FILESYSTEM;
        }
    }
    public void inviaDatiAlBean() throws IOException {
        try {
            beanVerificaDati=new BeanSegnalaEntita(Integer.toString(profondita),indirizzo, typeEntita,typeOfPersistence);
            //questi dati devono essere mandati al controller applicativo
            ControllerApplicativoSegnalazioneEntita controllerApplicativoSegnalazioneEntita=new ControllerApplicativoSegnalazioneEntita(beanVerificaDati);
            System.out.println("segnalazione avvenuta con successo\ntorna alla home =)\npremere qualsiasi tasto per tornare alla home: ");
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));
            if(bufferedReader.readLine().length()>=1){
                tornaAllaHome();
            }
        }catch(SQLException| ErroreLetturaPasswordException | SegnalazioneGiaAvvenutaException | NessunAccessoEffettuatoException | TipoEntitaException e) {
            System.out.println(e.getMessage());
            tornaAllaHome();
        }

    }
    private void tornaAllaHome() throws IOException {
        paginaHome.displayHomepage();
    }
}
