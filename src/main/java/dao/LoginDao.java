package dao;

import java.sql.SQLException;

public interface LoginDao {
    //questa interfaccia deve contenere tutti i metodi che mi aspetto di avere quando viene eseguito un login
    //deve quindi verificare che l'utente con quella email esista e password esista nel sistema
    boolean verificaAccountNelSistema(String email,String password) throws SQLException;
}
