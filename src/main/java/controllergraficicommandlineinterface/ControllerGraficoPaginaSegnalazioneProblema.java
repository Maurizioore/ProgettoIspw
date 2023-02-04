package controllergraficicommandlineinterface;

import cli.PaginaSegnalazionePaloIlluminazioneCli;
import java.io.IOException;


public class ControllerGraficoPaginaSegnalazioneProblema {
    public void segnalaPaloIlluminazione() throws IOException {
        PaginaSegnalazionePaloIlluminazioneCli paginaSegnalazionePaloIlluminazione=new PaginaSegnalazionePaloIlluminazioneCli();
        paginaSegnalazionePaloIlluminazione.inserisciInput();

    }
}
