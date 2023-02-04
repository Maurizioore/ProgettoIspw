package dao;

import com.example.progettoispw.controllergrafici.TypeOfSegnalazione;
import eccezioni.ErroreLetturaPasswordException;
import eccezioni.NonEsistonoSegnalazioniException;

import java.sql.SQLException;
import java.util.List;

public interface SegnalazioniRisolteAttiveDao {
    public void cercaSegnalazioni(List<String> listaPaliSegnalati, List<String> listaIndirizziDiQueiPali, List<String> listaStatoDelleSegnalazioni, List<String> listaProfonditaBucheSegnalate, List<String> listaIndirizzoBuche, List<String> listaStatoBuche, TypeOfSegnalazione typeOfSegnalazione)throws SQLException, NonEsistonoSegnalazioniException, ErroreLetturaPasswordException;
}
