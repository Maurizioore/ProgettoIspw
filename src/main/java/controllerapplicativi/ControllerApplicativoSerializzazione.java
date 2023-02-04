package controllerapplicativi;

import bean.BeanImpostazioni;
import entita.Impostazioni;

import java.io.*;

public class ControllerApplicativoSerializzazione {
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private String nome;
    private String cognome;
    private String sesso;
    private int eta;
    public ControllerApplicativoSerializzazione(){
        //non fa nulla quello di default
    }
    public ControllerApplicativoSerializzazione(BeanImpostazioni beanImpostazioni){
        //quello non di default imposta le variabili locali con le variabili corrispettive del bean
        this.nome=beanImpostazioni.getNome();
        this.cognome=beanImpostazioni.getCognome();
        this.sesso=beanImpostazioni.getSesso();
        this.eta=beanImpostazioni.getEta();
    }
    public void salvaInformazioni() throws IOException {
        //questo metodo serializza l'oggetto, creo quindi l'entita impostazioni e la serializzo in un file impostazioni.ser
        Impostazioni impostazioni = new Impostazioni(nome, cognome, eta, sesso);
        //serializziamo l'oggetto
        try {
            objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("impostazioni.ser")));
            //scrivo l'oggetto nel file impostazioni.ser
            objectOutputStream.writeObject(impostazioni);
        } catch (IOException e) {
            throw new IOException("c'e' stato un errore nel salvataggio");
        } finally {
            //eccezioni o no se ho aperto lo stream, lo chiudo
            if (objectOutputStream != null) {
                objectOutputStream.close();
            }
        }
    }
    public void prendiInformazioni(BeanImpostazioni beanImpostazioni) throws IOException, ClassNotFoundException {
        //questo metodo è usato per la deserializzazione di un oggetto di tipo Impostazioni
        try {
            objectInputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream("impostazioni.ser")));
            Impostazioni impostazioni = (Impostazioni) objectInputStream.readObject();
            objectInputStream.close();
            //do al bean i valori dell'oggetto che ho recuperato e questi valori verrano presi dal controller grafico successivamente
            beanImpostazioni.setCognome(impostazioni.getCognome());
            beanImpostazioni.setEta(impostazioni.getEta());
            beanImpostazioni.setNome(impostazioni.getNome());
            beanImpostazioni.setSesso(impostazioni.getSesso());
    } catch (ClassNotFoundException | IOException e) {
            // se la classe non viene trovata non è una tragedia, vuol semplicemente dire che l'utente e' la prima volta
            //che starà modificando le credenziali
            throw new IOException("inserisci le credenziali, rimarranno sempre salvate");
        }finally {
            if(objectInputStream!=null){
                objectInputStream.close();
            }
        }
    }
}
