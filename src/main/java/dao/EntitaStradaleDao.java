package dao;

import eccezioni.ErroreLetturaPasswordException;
import eccezioni.SegnalazioneGiaAvvenutaException;
import entita.EntitaStradale;
import java.io.IOException;
import java.sql.SQLException;

public interface EntitaStradaleDao {
    //interfaccia che mette a disposizione ai dao cosa possono fare se un utente vuole slavare in locale
    //o nel db un entità stradale
    void saveEntitaStradale(EntitaStradale instance) throws SQLException, SegnalazioneGiaAvvenutaException, ErroreLetturaPasswordException, IOException;
}
