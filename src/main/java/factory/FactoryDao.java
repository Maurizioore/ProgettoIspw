package factory;

import dao.*;
import eccezioni.ErroreLetturaPasswordException;
import java.sql.SQLException;

public class FactoryDao {
    //factory che con il metodo useDao restituisce un kind of EntitaStradaleDao in base al tipo dell'entita e al tipo di persistenza
    public EntitaStradaleDao useDao(TypeOfPersistence typeOfPersistence, TypeEntita typeEntita) throws SQLException, ErroreLetturaPasswordException {
        if (typeOfPersistence == TypeOfPersistence.JDBC) {
            switch (typeEntita) {
                case type_buca_stradale:
                    return new BucaStradaleDaoImplJDBC();
                default:
                    return new PaloIlluminazioneDaoImplJDBC();
            }
        } else {
                switch (typeEntita) {
                    case type_buca_stradale:
                        return new BucaStradaleDaoImplFileSystem();
                    default:
                        return new PaloIlluminazioneDaoImplFileSystem();
                }
        }
    }
}
