package dao;

import eccezioni.ErroreLetturaPasswordException;
import eccezioni.SegnalazioneGiaAvvenutaException;
import entita.EntitaStradale;
import entita.PaloIlluminazione;
import queries.QueriesPaloIlluminazione;
import utilityaccesso.UtilityAccesso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaloIlluminazioneDaoImplJDBC implements EntitaStradaleDao{
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    public PaloIlluminazioneDaoImplJDBC() throws SQLException, ErroreLetturaPasswordException {
        connection=SingletonConnessione.getInstance();
    }
    @Override
    public void saveEntitaStradale(EntitaStradale instance) throws SQLException, SegnalazioneGiaAvvenutaException, ErroreLetturaPasswordException {
        //qui so che ho a che fare con un palo alla fine e lo devo mandare al db , allora rendo l'entita stradale un palo
        //e poi lo invio
        PaloIlluminazione paloIlluminazioneDaSegnalare=new PaloIlluminazione(instance.infoEntita(),instance.getIndirizzo());
        //ora lo invio
        //questa operazione può essere fatta da tutti, sia loggati che non
        //vedo se il palo e' già presente o no nel db, se lo e' comunico che gi esiste nel db, altrimenti lo salvo nel db
        if(!cercaPalo(paloIlluminazioneDaSegnalare)){
            if(UtilityAccesso.getCodiceUtente()!=null){
                //l'utente è loggato nel sistema, la sua segnalazione deve essere salvata nel db
                preparedStatement=connection.prepareStatement(QueriesPaloIlluminazione.queriesSalvaPaloAdUnUtenteDelSistema());
                preparedStatement.setString(1,paloIlluminazioneDaSegnalare.numeroSerialePalo);
                preparedStatement.setString(2,paloIlluminazioneDaSegnalare.indirizzo);
                preparedStatement.setString(3,UtilityAccesso.getCodiceUtente());
                preparedStatement.executeUpdate();
            }else {
                preparedStatement = connection.prepareStatement(QueriesPaloIlluminazione.queriesSalvaPalo());
                preparedStatement.setString(1, paloIlluminazioneDaSegnalare.numeroSerialePalo);
                preparedStatement.setString(2, paloIlluminazioneDaSegnalare.indirizzo);
                preparedStatement.executeUpdate();
            }
        }else {
            throw new SegnalazioneGiaAvvenutaException("il palo è stato già segnalato da un altro utente");
        }
    }
    private boolean cercaPalo(PaloIlluminazione paloDaCercare) throws SQLException, ErroreLetturaPasswordException {
        //verifichiamo che la connessione sia aperta prima
        verificaConnessione();
        preparedStatement=connection.prepareStatement(QueriesPaloIlluminazione.cercaPaloIlluminazione());
        preparedStatement.setString(1,paloDaCercare.numeroSerialePalo);
        preparedStatement.setString(2,paloDaCercare.indirizzo);
        resultSet=preparedStatement.executeQuery();
        return resultSet.isBeforeFirst();
    }
    private void verificaConnessione() throws SQLException, ErroreLetturaPasswordException {
        if(connection==null){
            new PaloIlluminazioneDaoImplJDBC();
        }
    }
}
