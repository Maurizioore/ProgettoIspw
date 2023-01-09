package dao;

import eccezioni.DuplicazioneInputException;
import eccezioni.SegnalazioneGiaAvvenutaException;
import entita.PaloIlluminazione;
import queries.QueriesPaloIlluminazione;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PaloIlluminazioneDaoImpl implements PaloIlluminazioneDao{
    /*classe che parla con il database, qui ci sono i metodi grazie a cui posso ottenere un oggetto palo dal mio db
    * posso salvare un oggetto palo nel db, posso rimuovere un oggetto palo nel db, e posso cambiare stato al mio
    * oggetto nel db  */
    private Connection connection=null;
    private PreparedStatement preparedStatement=null;
    private ResultSet resultSet=null;
    private void verificaConnessione() throws SQLException {
        if(connection==null){
            new PaloIlluminazioneDaoImpl();
        }
    }
    public PaloIlluminazioneDaoImpl() throws SQLException {
        connection = SingletonConnessione.getInstance();
    }
    @Override
    public List<PaloIlluminazione> getPaloIlluminazione(List<PaloIlluminazione> instance) throws SQLException {
        /*questo metodo puo' essere chiamato da un utente che vuole vedere le segnalazioni che lui ha fatto
        * quindi il numero seriale del palo e lo stato del palo, perche alla fine ad un utente interessano queste 2 cose
        * quindi è piu sensato che ritorni una lista di pali in cui l'utente abbia più di una segnalazione*/
        //verifico prima che la connessione sia aperta
        verificaConnessione();
        //questa operazione può essere fatta solo se l'utente e' loggato e viene svolta quando clicco su attive
        preparedStatement = connection.prepareStatement(QueriesPaloIlluminazione.restituisciPalo());
        resultSet = preparedStatement.executeQuery();
        //isBeforeFirst ritorna true se il result set contiene qualcosa, altrimenti ritorna falso
        if (!resultSet.isBeforeFirst()) {
            throw new SQLException("nessun palo ancora segnalato");
        }
        while (resultSet.next()) {
            //prendo il numero seriale e l'indirizzo del palo e creo un oggetto palo che inserisco nella lista
            String numeroSerialePalo = resultSet.getString("numeroSeriale");
            String indirizzo = resultSet.getString("indirizzo");
            PaloIlluminazione paloIlluminazione = new PaloIlluminazione(numeroSerialePalo, indirizzo);
            instance.add(paloIlluminazione);
        }
        return instance;
    }
    @Override
    public void savePaloIlluminazione(PaloIlluminazione instance) throws  SQLException, SegnalazioneGiaAvvenutaException{
        /*questo metodo viene chiamato quando un palo ha passato tutte le verifiche e puo' essere quindi aggiunto nel
        * database, non deve restituire nulla, deve salvare il palo nel db, e nel caso in cui un palo sia già presente
        * voglio che all'utente venga detto che la segnalazione è stata già presa in carico*/
        //verifico che la connessione sia aperta
        verificaConnessione();
        //questa operazione può essere fatta da tutti, sia loggati che non
        //vedo se il palo e' già presente o no nel db, se lo e' comunico che gi esiste nel db, altrimenti lo salvo nel db
        if(!cercaPalo(instance)){
            preparedStatement = connection.prepareStatement(QueriesPaloIlluminazione.queriesSalvaPalo());
            preparedStatement.setString(1, instance.numeroSerialePalo);
            preparedStatement.setString(2, instance.indirizzo);
            preparedStatement.executeUpdate();
        }else {
            throw new SegnalazioneGiaAvvenutaException("il palo è stato già segnalato");
        }
    }

    @Override
    public void removePaloIlluminazione(String numeroSerialePalo,String indirizzo) throws SQLException, DuplicazioneInputException {
        /*questo metodo serve per rimuove un palo dal db, verrà svolto dall'admin una volta ogni 2 mesi */
        //vediamo se la connessione e' aperta
        verificaConnessione();
        //vediamo prima se il palo che stiamo rimuovendo esiste, se esiste lo posso rimuovere altrimenti viene comunicato
        //che quel palo inserito ancora non e' stato segnalato e quindi già non presente nel db
        PaloIlluminazione paloIlluminazione=new PaloIlluminazione(numeroSerialePalo,indirizzo);
        if(cercaPalo(paloIlluminazione)) {
            preparedStatement = connection.prepareStatement(QueriesPaloIlluminazione.queriesRimuoviPalo());
            preparedStatement.setString(1, numeroSerialePalo);
            preparedStatement.setString(2, indirizzo);
            preparedStatement.executeUpdate();
        }else{
            throw new DuplicazioneInputException("il palo inserito non e' stato ancora segnalato");
        }

    }
    @Override
    public void cambiaStatoPaloIlluminazione(String nuovoStato,PaloIlluminazione paloACuiCambiareStato) throws SQLException, DuplicazioneInputException {
        /*questo metodo aggiorna lo stato di un palo e puo' essere svolto dall'admin dopo che la
        * riparazione e' andata a buon fine */
        //verifichiamo che la connessione sia aperta
        verificaConnessione();
        //vediamo prima se il palo c'e'
        if(cercaPalo(paloACuiCambiareStato)==true){
            //cercaPalo ritorna true se il palo a cui voglio cambiare stato esiste, quindi gli cambio stato
            if (nuovoStato.equals("riparato") || nuovoStato.equals("in corso di riparazione")) {
                //allora si può cambiare stato
                preparedStatement = connection.prepareStatement(QueriesPaloIlluminazione.cambiaStatoPalo());
                preparedStatement.setString(1, nuovoStato);
                preparedStatement.setString(2, paloACuiCambiareStato.numeroSerialePalo);
                preparedStatement.setString(3, paloACuiCambiareStato.indirizzo);
                preparedStatement.executeUpdate();
            } else {
                //crea un eccezione per questo e fagli lanciare quella eccezione
                throw new SQLException("passare uno stato valido");
            }
        }else{
            throw new DuplicazioneInputException("il palo inserito non e' stato segnalato");
        }
    }
    //questo metodo ritorna true se il palo che sto cercando nel database e' effettivamente presente in esso, false altrimenti
    private boolean cercaPalo(PaloIlluminazione paloDaCercare) throws SQLException {
        //verifichiamo che la connessione sia aperta prima
        verificaConnessione();
        preparedStatement=connection.prepareStatement(QueriesPaloIlluminazione.cercaPaloIlluminazione());
        preparedStatement.setString(1,paloDaCercare.numeroSerialePalo);
        preparedStatement.setString(2,paloDaCercare.indirizzo);
        resultSet=preparedStatement.executeQuery();
        return resultSet.isBeforeFirst();
    }
}
