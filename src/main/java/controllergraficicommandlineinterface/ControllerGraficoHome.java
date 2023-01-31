package controllergraficicommandlineinterface;

import cli.PaginaSegnalazioneProblemaCli;

import java.io.IOException;

public class ControllerGraficoHome {

    public void segnalaProblema() throws IOException {
        PaginaSegnalazioneProblemaCli paginaSegnalazioneProblema=new PaginaSegnalazioneProblemaCli();
        paginaSegnalazioneProblema.mostraProblemiChePossonoEssereSegnalati();
    }

}
