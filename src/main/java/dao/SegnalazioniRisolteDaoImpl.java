package dao;

import eccezioni.ErroreLetturaPasswordException;
import eccezioni.NonEsistonoSegnalazioniException;
import queries.QueriesPaloIlluminazione;
import utilityaccesso.UtilityAccesso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SegnalazioniRisolteDaoImpl implements SegnalazioniRisolteDao{
    //come tutti i dao lui si preoccupa di interrogare il db

    private Connection connection;
    private void verificaConnessione() throws SQLException, ErroreLetturaPasswordException {
        if(connection==null){
            connection=SingletonConnessione.getInstance();
        }
    }
    public SegnalazioniRisolteDaoImpl() throws SQLException, ErroreLetturaPasswordException {
        //il costruttore non fa altro che aprire o prendere una connessione verso il db
        connection=SingletonConnessione.getInstance();

    }

    //metodo che cerca le segnalazioni che l'utente ha aperto e che sono state risolte
    @Override
    public void cercaSegnalazioniRisolte(List<String> listaPaliSegnalati, List<String> listaIndirizziDiQueiPali) throws SQLException, NonEsistonoSegnalazioniException, ErroreLetturaPasswordException {
        //verifico prima che la connessione sia ancora aperta( magari qualcun altro dao l'ha chiusa
        verificaConnessione();
        //ora devo richiedere al sistema le segnalazioni dell'utente
        PreparedStatement preparedStatement=connection.prepareStatement(QueriesPaloIlluminazione.queriesMostraSegnalazioniCompletate());
        preparedStatement.setString(1, UtilityAccesso.getCodiceUtente());
        ResultSet rs=preparedStatement.executeQuery();
        //ora devo vedere se la l'utente ha segnalato veramente qualcosa
        //isBeforeFirst ritorna true se il result set contiene qualcosa, quindi se l'utente ha effettuato la segnalazione
        if(!rs.isBeforeFirst()){
            throw new NonEsistonoSegnalazioniException("Non hai effettuato nessuna segnalazione");
        }
        //il result set contiene quindi qualcosa, questo qualcosa lo prendo e lo inserisco nella lista
        while(rs.next()){
            listaPaliSegnalati.add(rs.getString("numeroSeriale"));

            listaIndirizziDiQueiPali.add(rs.getString("indirizzo"));
        }
    }
}

