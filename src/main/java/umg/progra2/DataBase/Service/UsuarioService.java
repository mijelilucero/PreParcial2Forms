package umg.progra2.DataBase.Service;

import umg.progra2.DataBase.ConfigDB.DatabaseConnection;
import umg.progra2.DataBase.ConfigDB.TransactionManager;
import umg.progra2.DataBase.Dao.UsuarioDao;
import umg.progra2.DataBase.Model.Usuario;

import java.sql.Connection;
import java.sql.SQLException;

public class UsuarioService {

    private UsuarioDao userDao = new UsuarioDao();

    public void deleteUserByEmail(String email) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            TransactionManager tm = new TransactionManager(connection);
            tm.beginTransaction();
            try {
                userDao.deleteUserByEmail(email);
                tm.commit();
            } catch (SQLException e) {
                tm.rollback();
                throw e;
            }
        }
    }

    public void createUser(Usuario user) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            TransactionManager tm = new TransactionManager(connection);
            tm.beginTransaction();
            try {
                userDao.insertUser(user);
                tm.commit();
            } catch (SQLException e) {
                tm.rollback();
                throw e;
            }
        }
    }

    public void updateUser(Usuario user) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            TransactionManager tm = new TransactionManager(connection);
            tm.beginTransaction();
            try {
                userDao.updateUser(user);
                tm.commit();
            } catch (SQLException e) {
                tm.rollback();
                throw e;
            }
        }
    }


    public Usuario getUserByTelegramId(long telegramid) throws SQLException {
        return userDao.getUserByTelegramId(telegramid);
    }

    public Usuario getUserByEmail(String Email) throws SQLException {
        return userDao.getUserByEmail(Email);
    }

    public Usuario getUserByCarne(String Carne) throws SQLException {
        return userDao.getUserByCarne(Carne);
    }

}
