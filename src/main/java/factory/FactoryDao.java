package factory;

import dao.*;
import eccezioni.ErroreLetturaPasswordException;
import eccezioni.TipoEntitaException;

import java.io.IOException;
import java.sql.SQLException;

public class FactoryDao {
    public EntitaStradaleDao useDao(TypeOfPersistence typeOfPersistence, TypeEntita typeEntita) throws TipoEntitaException, SQLException, ErroreLetturaPasswordException, IOException {
        switch (typeOfPersistence){
            case JDBC:
                switch(typeEntita){
                    case type_palo_illuminazione :
                        return new PaloIlluminazioneDaoImplJDBC();
                    case type_buca_stradale:
                        return new BucaStradaleDaoImplJDBC();
                }
            case FILESYSTEM:
                switch (typeEntita){
                    case type_palo_illuminazione:
                        return new PaloIlluminazioneDaoImplFileSystem();
                    case type_buca_stradale:
                        return new BucaStradaleDaoImplFileSystem();
                }
            default:
                throw  new TipoEntitaException("segnalare un palo o una buca");
        }
    }
}
