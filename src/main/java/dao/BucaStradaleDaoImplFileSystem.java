package dao;

import eccezioni.ErroreLetturaPasswordException;
import eccezioni.SegnalazioneGiaAvvenutaException;
import entita.BucaStradale;
import entita.EntitaStradale;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
public class BucaStradaleDaoImplFileSystem implements EntitaStradaleDao{
    //dao che si occuopa di salvare l'entita stradale nel file system
    private static final String CSV_FILE_NAME = "BucaStradaleSegnalata.txt";

    @Override
    public void saveEntitaStradale(EntitaStradale instance) throws SQLException, SegnalazioneGiaAvvenutaException, ErroreLetturaPasswordException {
        //se sono qui voglio salvare su file system la buca
        BucaStradale bucaStradale = new BucaStradale(instance.infoEntita(), instance.getIndirizzo());
        //adesso devo salvarla in locale
        try {
            //imposto a true il secondo parametro del costruttore del file writer, in questo modo non c'e' sovrascrittura
            BufferedWriter fileWriter=new BufferedWriter(new FileWriter(CSV_FILE_NAME,true));
            String bucaSegnalataDaSalvareInLocale=convertiBucaInTxt(bucaStradale);
            fileWriter.write(bucaSegnalataDaSalvareInLocale);
            //vado a capo cosi la prossima volta che si scrive su quel file e' tutto piu ordinato e la segnalazione
            //nuova non si attaccherà alla vecchia
            fileWriter.newLine();
            fileWriter.close();
        } catch (IOException e) {
            throw new SQLException("problema con il file writer");
        }
    }
    private String convertiBucaInTxt(BucaStradale bucaStradale){
        return "Profondità buca segnalata "+ bucaStradale.profondita+"\nindirizzo: "+bucaStradale.indirizzo+"\nstato: ancora non segnalato al database";
    }
}
