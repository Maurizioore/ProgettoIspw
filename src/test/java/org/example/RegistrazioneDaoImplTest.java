package org.example;

import dao.RegistrazioneDaoImpl;
import eccezioni.ErroreLetturaPasswordException;
import eccezioni.UtenteEsistenteException;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class RegistrazioneDaoImplTest {
    //test sulla registrazione
    @Test
    void registraUtente() throws SQLException, ErroreLetturaPasswordException, UtenteEsistenteException {
        RegistrazioneDaoImpl registrazioneDao=new RegistrazioneDaoImpl();
        // se l'utente gia esiste nel sistema mi aspetto false come risultato, altrimenti true
        assertEquals(true,registrazioneDao.registraUtente("nuovoUtente12d","nuovoUtente1d2@gmail.com","nuovoUtente"));
    }

    @Test
    void verificaEsistenzaUtente() throws SQLException, ErroreLetturaPasswordException, UtenteEsistenteException {
        RegistrazioneDaoImpl registrazioneDao=new RegistrazioneDaoImpl();
        //verificaEsistenzaUtente torna false se esiste già un utente con quelle credenziali, true altrimenti
        assertEquals(true,registrazioneDao.verificaEsistenzaUtente("nuovoUtente1","nuovoUtente1Test@gmail.com"));
    }
}