package dao;

import eccezioni.ErroreLetturaPasswordException;
import eccezioni.SegnalazioneGiaAvvenutaException;
import entita.EntitaStradale;
import entita.PaloIlluminazione;

import java.sql.SQLException;

public interface EntitaStradaleDao {
    //voglio poter salvare un entita stradale nel sistema
    //rimuoverla
    //cambiarle stato
    //nota bisogna anche fare i controlli se i pali sono stati gia segnalati, a quella che hai fatto prima sta cosa
    //la facevi con un controllo nelle classi contenitore, qui falla direttamente sul db
    void saveEntitaStradale(EntitaStradale instance) throws SQLException, SegnalazioneGiaAvvenutaException, ErroreLetturaPasswordException;
}
