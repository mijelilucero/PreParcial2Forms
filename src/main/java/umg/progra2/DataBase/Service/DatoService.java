package umg.progra2.DataBase.Service;

import umg.progra2.DataBase.ConfigDB.DatabaseConnection;
import umg.progra2.DataBase.ConfigDB.TransactionManager;
import umg.progra2.DataBase.Dao.DatoDao;
import umg.progra2.DataBase.Model.Dato;

import java.sql.Connection;
import java.sql.SQLException;

public class DatoService {

    private DatoDao datoDao = new DatoDao();

    public void deleteDatoByCodigo(int codigo) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            TransactionManager tm = new TransactionManager(connection);
            tm.beginTransaction();
            try {
                datoDao.deleteDatoByCodigo(codigo);
                tm.commit();
            } catch (SQLException e) {
                tm.rollback();
                throw e;
            }
        }
    }

    public void createDato(Dato dato) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            TransactionManager tm = new TransactionManager(connection);
            tm.beginTransaction();
            try {
                datoDao.insertDato(dato);
                tm.commit();
            } catch (SQLException e) {
                tm.rollback();
                throw e;
            }
        }
    }

    public void updateDato(Dato dato) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            TransactionManager tm = new TransactionManager(connection);
            tm.beginTransaction();
            try {
                datoDao.updateDato(dato);
                tm.commit();
            } catch (SQLException e) {
                tm.rollback();
                throw e;
            }
        }
    }

    public Dato getDatoByCodigo(int codigo) throws SQLException {
        return datoDao.getDatoByCodigo(codigo);
    }

}
