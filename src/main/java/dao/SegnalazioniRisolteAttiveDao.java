package dao;

import com.example.progettoispw.controllergrafici.Type_of_segnalazione;
import eccezioni.ErroreLetturaPasswordException;
import eccezioni.NonEsistonoSegnalazioniException;
import java.sql.SQLException;
import java.util.List;

public interface SegnalazioniRisolteAttiveDao {
    public void cercaSegnalazioni(List<String> listaPaliSegnalati, List<String> listaIndirizziDiQueiPali, List<String> listaStatoDelleSegnalazioni, List<String> listaProfonditaBucheSegnalate, List<String> listaIndirizzoBuche, List<String> listaStatoBuche, Type_of_segnalazione type_of_segnalazione)throws SQLException, NonEsistonoSegnalazioniException, ErroreLetturaPasswordException;
}
