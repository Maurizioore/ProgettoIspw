package dao;

import eccezioni.ErroreLetturaPasswordException;
import eccezioni.NonEsistonoSegnalazioniException;

import java.sql.SQLException;
import java.util.List;

public interface SegnalazioniRisolteDao {
    //qui metto i metodi che il mio dao deve dare all'utente con le segnalazioni risolte
    void cercaSegnalazioniRisolte(List<String> listaPaliSegnalati, List<String> listaIndirizziDiQueiPali) throws SQLException, NonEsistonoSegnalazioniException, ErroreLetturaPasswordException;

}
