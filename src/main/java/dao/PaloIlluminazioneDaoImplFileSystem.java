package dao;

import eccezioni.ErroreLetturaPasswordException;
import eccezioni.SegnalazioneGiaAvvenutaException;
import entita.EntitaStradale;
import entita.PaloIlluminazione;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class PaloIlluminazioneDaoImplFileSystem implements EntitaStradaleDao{
    private static final String CSV_FILE_NAME = "PaloIlluminazioneSegnalato.txt";

    private File fd;

    private HashMap<String, PaloIlluminazione> localCache;

    public PaloIlluminazioneDaoImplFileSystem() throws IOException {
        try {
            this.fd = new File(CSV_FILE_NAME);
            if (!fd.exists()) {
                if(!fd.createNewFile()){
                    throw new IOException("creazione non riuscita");
                }
            }
            this.localCache = new HashMap<String, PaloIlluminazione>();
        }catch (IOException e){
            throw new IOException("salvataggio sul file non riuscito");
        }

    }
    @Override
    public void saveEntitaStradale(EntitaStradale instance) throws SQLException, SegnalazioneGiaAvvenutaException, ErroreLetturaPasswordException {
        //se sono qui voglio salvare su file system il palo
        PaloIlluminazione paloIlluminazione = new PaloIlluminazione(instance.infoEntita(), instance.getIndirizzo());
        //adesso devo salvarlo in locale
        try {
            BufferedWriter fileWriter=new BufferedWriter(new FileWriter(CSV_FILE_NAME));
            String paloSegnalatoDaMandareNelFileTxt=convertiPaloInTxt(paloIlluminazione);
            fileWriter.write(paloSegnalatoDaMandareNelFileTxt);
            fileWriter.close();
        } catch (IOException e) {
            throw new SQLException("problema con il file writer");
        }
    }
    private String convertiPaloInTxt(PaloIlluminazione paloIlluminazioneSegnalato){
        return "Numero seriale palo segnalato: "+paloIlluminazioneSegnalato.numeroSerialePalo+"\nindirizzo: "+paloIlluminazioneSegnalato.indirizzo+"\nstato: ancora non segnalato al database";
    }

}
