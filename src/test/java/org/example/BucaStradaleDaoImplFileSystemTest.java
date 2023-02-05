package org.example;

import dao.BucaStradaleDaoImplFileSystem;
import eccezioni.ErroreLetturaPasswordException;
import eccezioni.SegnalazioneGiaAvvenutaException;
import entita.BucaStradale;
import entita.EntitaStradale;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class BucaStradaleDaoImplFileSystemTest {
    private BucaStradaleDaoImplFileSystem bucaStradaleDaoImplFileSystem;

    @Test
    void saveBucaStradaleNelFileSystem(){
        //verifico che venga effettivamente salvata una buca stradale che sto segnalando nel fileSystem
        //in un formato .txt
        try {
            bucaStradaleDaoImplFileSystem = new BucaStradaleDaoImplFileSystem();
            EntitaStradale entitaStradale = new BucaStradale("32", "viaa");
            bucaStradaleDaoImplFileSystem.saveEntitaStradale(entitaStradale);
        }catch(SegnalazioneGiaAvvenutaException |SQLException |ErroreLetturaPasswordException |IOException e){
            //niente da fare qui
        }finally{
            //se la segnalazione è andata a buon fine esito varrà 0, quindi ho scritto sul file BucaStradaleSegnalata.txt
            assertEquals(0, bucaStradaleDaoImplFileSystem.getEsito());
        }
    }

}