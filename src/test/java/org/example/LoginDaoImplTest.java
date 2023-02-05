package org.example;

import dao.LoginDaoImpl;
import eccezioni.ErroreLetturaPasswordException;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;

class LoginDaoImplTest {
    //test sul login,

    @Test
    void verificaEsistenzaNelSistema() throws SQLException, ErroreLetturaPasswordException{
        //test che verifica se una email esiste nel sistema, qui vi passerò una email che e' quindi effettivamente
        //presente nel db
            LoginDaoImpl loginDao = new LoginDaoImpl();
            //true =email presente
            assertEquals(true, loginDao.verificaAccountNelSistema("emailprova@gmail.com", "prova"));
    }
    @Test
    void verificaNonEsistenzaNelSistema() throws  SQLException, ErroreLetturaPasswordException{
        //test che verifica se una mail non e'presente nel db, qui vi passerò una email non presente nel db
        LoginDaoImpl loginDao= new LoginDaoImpl();
        //false= email non presente
        assertEquals(false,loginDao.verificaAccountNelSistema("eailsd@gmail.com","siuf"));
    }
}