package dao;

import eccezioni.ErroreLetturaPasswordException;
import eccezioni.SegnalazioneGiaAvvenutaException;
import entita.BucaStradale;
import entita.EntitaStradale;
import entita.PaloIlluminazione;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class BucaStradaleDaoImplFileSystem implements EntitaStradaleDao{
    private static final String CSV_FILE_NAME = "BucaStradaleSegnalata.txt";

    private File fd;

    private HashMap<String, PaloIlluminazione> localCache;

    public BucaStradaleDaoImplFileSystem() throws IOException {
        try {
            this.fd = new File(CSV_FILE_NAME);
            if (!fd.exists()) {
                if(!fd.createNewFile()){
                    throw new IOException("errore nella creazione del file");
                };
            }
            this.localCache = new HashMap<String, PaloIlluminazione>();
        }catch (IOException e){
            throw new IOException("salvataggio sul file non riuscito");
        }

    }
    @Override
    public void saveEntitaStradale(EntitaStradale instance) throws SQLException, SegnalazioneGiaAvvenutaException, ErroreLetturaPasswordException {
        //se sono qui voglio salvare su file system la buca
        BucaStradale bucaStradale = new BucaStradale(instance.infoEntita(), instance.getIndirizzo());
        //adesso devo salvarlain locale
        try {
            BufferedWriter f_writer=new BufferedWriter(new FileWriter(CSV_FILE_NAME));
            String bucaSegnalataDaSalvareInLocale=convertiBucaInTxt(bucaStradale);
            f_writer.write(bucaSegnalataDaSalvareInLocale);
            f_writer.close();
        } catch (IOException e) {
            throw new SQLException("problema con il file writer");
        }
    }
    private String convertiBucaInTxt(BucaStradale bucaStradale){
        return "Profondità buca segnalata "+ bucaStradale.profondita+"\nindirizzo: "+bucaStradale.indirizzo+"\nstato: ancora non segnalato al database";
    }
}
