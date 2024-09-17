package umg.progra2.DataBase.ConfigDB;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {

    private final Connection connection;

    public TransactionManager(Connection connection) {
        this.connection = connection;
    }

    public void beginTransaction() throws SQLException {
        connection.setAutoCommit(false);
    }

    public void commit() throws SQLException {
        connection.commit();
    }

    public void rollback() throws SQLException {
        connection.rollback();
    }

}
