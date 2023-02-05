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
    //dao che si occupa di salvare l'entità stradale nel file system
    private static final String FILE_NAME = "BucaStradaleSegnalata.txt";
    //variabile che viene cambiata in base all'esito del salvataggio della buca stradale nel file system
    //molto utile nel momento di testare il metodo SaveEntitaStradale
    private int esitoSalvataggio;

    @Override
    public void saveEntitaStradale(EntitaStradale instance) throws SQLException, SegnalazioneGiaAvvenutaException, ErroreLetturaPasswordException, IOException {
        //se sono qui voglio salvare su file system la buca
        BucaStradale bucaStradale = new BucaStradale(instance.infoEntita(), instance.getIndirizzo());
        //adesso devo salvarla in locale
        try {
            //imposto a true il secondo parametro del costruttore del file writer, in questo modo non c'e' sovrascrittura
            BufferedWriter fileWriter=new BufferedWriter(new FileWriter(FILE_NAME,true));
            String bucaSegnalataDaSalvareInLocale=convertiBucaInTxt(bucaStradale);
            fileWriter.write(bucaSegnalataDaSalvareInLocale);
            //vado a capo cosi la prossima volta che si scrive su quel file e' tutto piu ordinato e la segnalazione
            //nuova non si attaccherà alla vecchia
            fileWriter.newLine();
            fileWriter.close();
            //tutto e' andato a buon fine, esito assumerà un valore che indica il successo
            esitoSalvataggio=0;
        } catch (IOException e) {
            esitoSalvataggio=1;
            throw new IOException("problema con il file writer");
        }
    }
    private String convertiBucaInTxt(BucaStradale bucaStradale){
        return "Profondità buca segnalata "+ bucaStradale.profondita+"\nindirizzo: "+bucaStradale.indirizzo+"\nstato: ancora non segnalato al database";
    }
    public int getEsito(){
        return this.esitoSalvataggio;
    }
}
