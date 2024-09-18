package umg.progra2.DataBase.Service;

import umg.progra2.DataBase.ConfigDB.DatabaseConnection;
import umg.progra2.DataBase.ConfigDB.TransactionManager;
import umg.progra2.DataBase.Dao.EquipoChampionsDao;
import umg.progra2.DataBase.Model.EquipoChampions;

import java.sql.Connection;
import java.sql.SQLException;

public class EquipoChampionsService {

    private EquipoChampionsDao equipoChampionsDao = new EquipoChampionsDao();

    public void deleteEquipoChampionsById(int idEquipo) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            TransactionManager tm = new TransactionManager(connection);
            tm.beginTransaction();
            try {
                equipoChampionsDao.deleteEquipoChampionsById(idEquipo);
                tm.commit();
            } catch (SQLException e) {
                tm.rollback();
                throw e;
            }
        }
    }

    public void createEquipoChampions(EquipoChampions equipo) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            TransactionManager tm = new TransactionManager(connection);
            tm.beginTransaction();
            try {
                equipoChampionsDao.insertEquipoChampions(equipo);
                tm.commit();
            } catch (SQLException e) {
                tm.rollback();
                throw e;
            }
        }
    }

    public void updateEquipoChampions(EquipoChampions equipo) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            TransactionManager tm = new TransactionManager(connection);
            tm.beginTransaction();
            try {
                equipoChampionsDao.updateEquipoChampions(equipo);
                tm.commit();
            } catch (SQLException e) {
                tm.rollback();
                throw e;
            }
        }
    }

    public EquipoChampions getEquipoChampionsById(int idEquipo) throws SQLException {
        return equipoChampionsDao.getEquipoChampionsById(idEquipo);
    }

}
