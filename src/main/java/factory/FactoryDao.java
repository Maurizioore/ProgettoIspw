package factory;

import dao.*;
import eccezioni.ErroreLetturaPasswordException;
import java.sql.SQLException;

public class FactoryDao {
    //factory che con il metodo useDao restituisce un kind of EntitaStradaleDao in base al tipo dell'entita e al tipo di persistenza
    public EntitaStradaleDao useDao(TypeOfPersistence typeOfPersistence, TypeEntita typeEntita) throws SQLException, ErroreLetturaPasswordException {
        if (typeOfPersistence == TypeOfPersistence.JDBC) {
            if (typeEntita == TypeEntita.BUCASTRADALE) {
                return new BucaStradaleDaoImplJDBC();
            }
            return new PaloIlluminazioneDaoImplJDBC();
        }else{
            if(typeEntita==TypeEntita.BUCASTRADALE) {
                return new BucaStradaleDaoImplFileSystem();
            }
            return new PaloIlluminazioneDaoImplFileSystem();
        }
    }
}
