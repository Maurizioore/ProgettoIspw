package dao;

import eccezioni.DuplicazioneInputException;
import eccezioni.ErroreLetturaPasswordException;
import eccezioni.SegnalazioneGiaAvvenutaException;
import entita.PaloIlluminazione;
import java.sql.SQLException;
import java.util.List;

public interface PaloIlluminazioneDao {
    /*questa interfaccia deve avere tutti i metodi che devo svolgere sull'entità palo illuminazione
    * voglio poter aggiungere, cercare, eliminare, aggiornare un palo dell'illuminazione*/
    List<PaloIlluminazione> getPaloIlluminazione(List<PaloIlluminazione> instance) throws SQLException, ErroreLetturaPasswordException;
    void savePaloIlluminazione(PaloIlluminazione instance) throws SQLException, SegnalazioneGiaAvvenutaException, ErroreLetturaPasswordException;
    void removePaloIlluminazione(String numeroSerialePalo,String indirizzo) throws SQLException, DuplicazioneInputException, ErroreLetturaPasswordException;
    void cambiaStatoPaloIlluminazione(String nuovoStato,PaloIlluminazione paloACuiCambiareStato) throws SQLException, DuplicazioneInputException, ErroreLetturaPasswordException;
}
