package dao;

import eccezioni.ErroreLetturaPasswordException;
import eccezioni.SegnalazioneGiaAvvenutaException;
import entita.EntitaStradale;
import entita.PaloIlluminazione;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;


public class PaloIlluminazioneDaoImplFileSystem implements EntitaStradaleDao{
    //dao che salva l'entità stradale, in questo caso un palo illuminazione nel file system
    private static final String CSV_FILE_NAME = "PaloIlluminazioneSegnalato.txt";
    @Override
    public void saveEntitaStradale(EntitaStradale instance) throws SQLException, SegnalazioneGiaAvvenutaException, ErroreLetturaPasswordException {
        //se sono qui voglio salvare su file system il palo
        PaloIlluminazione paloIlluminazione = new PaloIlluminazione(instance.infoEntita(), instance.getIndirizzo());
        //adesso devo salvarlo in locale
        try {
            //secondo argomento messo a true , spiegazione in BucaStradaleDaoImplFileSystem
            BufferedWriter fileWriter=new BufferedWriter(new FileWriter(CSV_FILE_NAME,true));
            String paloSegnalatoDaMandareNelFileTxt=convertiPaloInTxt(paloIlluminazione);
            fileWriter.write(paloSegnalatoDaMandareNelFileTxt);
            //spiegazione in BucaStradaleDaoImplFileSystem per questo uso di newLine()
            fileWriter.newLine();
            fileWriter.close();
        } catch (IOException e) {
            throw new SQLException("problema con il file writer");
        }
    }
    private String convertiPaloInTxt(PaloIlluminazione paloIlluminazioneSegnalato){
        return "Numero seriale palo segnalato: "+paloIlluminazioneSegnalato.numeroSerialePalo+"\nindirizzo: "+paloIlluminazioneSegnalato.indirizzo+"\nstato: ancora non segnalato al database";
    }

}
