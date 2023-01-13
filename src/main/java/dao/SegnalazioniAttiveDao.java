package dao;

import eccezioni.ErroreLetturaPasswordException;
import eccezioni.NonEsistonoSegnalazioniException;

import java.sql.SQLException;
import java.util.List;

public interface SegnalazioniAttiveDao {
    //questa interfaccia definisce tutte le operazioni che voglio fare con le segnalazioni attive
    //per ora voglio solo vederle
    void cercaSegnalazioniAttive(List<String> listaPaliSegnalati, List<String> listaIndirizziDiQueiPali,List<String> listaStatoDelleSegnalazioni) throws SQLException, NonEsistonoSegnalazioniException, ErroreLetturaPasswordException;
}
