package controllergraficicommandlineinterface;

import cli.PaginaAccessoAlSistema;
import java.io.IOException;

public class ControllerGraficoLoginCli {
    private PaginaAccessoAlSistema paginaAccessoAlSistema;
    public void accediAlSistema() throws IOException {
        this.paginaAccessoAlSistema=new PaginaAccessoAlSistema();
        paginaAccessoAlSistema.mostraPaginaAccesso();
    }
}
