package Dao;

import dao.BucaStradaleDaoImplFileSystem;
import dao.BucaStradaleDaoImplJDBC;
import eccezioni.ErroreLetturaPasswordException;
import eccezioni.SegnalazioneGiaAvvenutaException;
import entita.BucaStradale;
import entita.EntitaStradale;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class BucaStradaleDaoImplJDBCTest {
    BucaStradaleDaoImplJDBC bucaStradaleDaoImp;

    @Test
    void saveBucaStradaleNonPresenteNelDb() {
        //test per salvare una buca stradale che non e' presente nel db
        try{
            bucaStradaleDaoImp=new BucaStradaleDaoImplJDBC();
            EntitaStradale entitaStradale = new BucaStradale("34", "vdaapoi");
            bucaStradaleDaoImp.saveEntitaStradale(entitaStradale);
        }catch(SegnalazioneGiaAvvenutaException | SQLException | ErroreLetturaPasswordException e){
            //niente da fare qui
        }finally {
            assertEquals(0,bucaStradaleDaoImp.getEsito());
        }
    }
    @Test
    void saveBucaStradaleGiaPresenteNelDb(){
        //test che salva nel db una buca stradale che già esiste nel db, quindi dovrà essere -1 perche
        //per come e' costruito il sistema non posso inserire 2 buche stradali identiche
        try{
            bucaStradaleDaoImp = new BucaStradaleDaoImplJDBC();
            EntitaStradale entitaStradale = new BucaStradale("16", "j");
            bucaStradaleDaoImp.saveEntitaStradale(entitaStradale);
        }catch(SegnalazioneGiaAvvenutaException | SQLException | ErroreLetturaPasswordException e){
            //niente da fare qui
        }finally {
            assertEquals(-1, bucaStradaleDaoImp.getEsito());
        }
    }
}