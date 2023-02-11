package Dao;

import dao.PaloIlluminazioneDaoImplJDBC;
import eccezioni.ErroreLetturaPasswordException;
import eccezioni.SegnalazioneGiaAvvenutaException;
import entita.PaloIlluminazione;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class PaloIlluminazioneDaoImplJDBCTest {
    private PaloIlluminazioneDaoImplJDBC paloIlluminazioneDaoImplJDBC;

    @Test
    void saveEntitaStradaleGiaPresente() {
        //inserisco un palo già presente, se è vero viene lanciata un eccezione, e quindi verifico in quella eccezione
        //che il palo sia effettivamente presente( se provo a mettere un palo con un indirizzo non presenti nel datatabase
        //il test fallirà
        try {
            paloIlluminazioneDaoImplJDBC = new PaloIlluminazioneDaoImplJDBC();
            PaloIlluminazione paloIlluminazione = new PaloIlluminazione("123456789123", "via maia");
            paloIlluminazioneDaoImplJDBC.saveEntitaStradale(paloIlluminazione);
            //se il palo che inserisco e' presente gia nel database mi aspetto che esito venga impostata a -1
        }catch (SQLException | ErroreLetturaPasswordException | SegnalazioneGiaAvvenutaException e){
            //non voglio fare niente qui nel test
        }finally {
            //vedo se effettivamente il palo che ho passato non esiste nella tabella
            assertEquals(-1, paloIlluminazioneDaoImplJDBC.getEsito());
        }
    }
    @Test
    void saveEntitaStradaleNonPresente(){
        //test che verifica se viene salvato nel db un palo dell'illuminazione che effettivamente non e' presente nel db
        try {
            paloIlluminazioneDaoImplJDBC =new PaloIlluminazioneDaoImplJDBC();
            PaloIlluminazione paloIlluminazione=new PaloIlluminazione("46as738298463","via roma");
            paloIlluminazioneDaoImplJDBC.saveEntitaStradale(paloIlluminazione);
        }catch (SQLException | ErroreLetturaPasswordException | SegnalazioneGiaAvvenutaException e){
            //non faccio nulla
        }finally {
            //vedo se il palo che ho passato che non era presente nel db e' stato inserito correttamente
            assertEquals(0,paloIlluminazioneDaoImplJDBC.getEsito());
        }
    }
}