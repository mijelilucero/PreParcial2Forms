package umg.progra2.DataBase.Dao;

import umg.progra2.DataBase.ConfigDB.DatabaseConnection;
import umg.progra2.DataBase.Model.Dato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatoDao {

    public void deleteDatoByCodigo(int codigo) throws SQLException {
        String query = "DELETE FROM tb_datos WHERE codigo = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, codigo);
            statement.executeUpdate();
        }
    }

    public void updateDato(Dato dato) throws SQLException {
        String query = "UPDATE tb_datos SET nombre = ?, apellido = ?, departamento = ?, fecha_nacimiento = ? WHERE codigo = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, dato.getNombre());
            statement.setString(2, dato.getApellido());
            statement.setString(3, dato.getDepartamento());
            statement.setDate(4, dato.getFechaNacimiento());
            statement.setInt(5, dato.getCodigo());
            statement.executeUpdate();
        }
    }

    public void insertDato(Dato dato) throws SQLException {
        String query = "INSERT INTO tb_datos (nombre, apellido, departamento, fecha_nacimiento) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, dato.getNombre());
            statement.setString(2, dato.getApellido());
            statement.setString(3, dato.getDepartamento());
            statement.setDate(4, dato.getFechaNacimiento());
            statement.executeUpdate();
        }
    }

    public Dato getDatoByCodigo(int codigo) throws SQLException {
        String query = "SELECT * FROM tb_datos WHERE codigo = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, codigo);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Dato dato = new Dato();
                dato.setCodigo(resultSet.getInt("codigo"));
                dato.setNombre(resultSet.getString("nombre"));
                dato.setApellido(resultSet.getString("apellido"));
                dato.setDepartamento(resultSet.getString("departamento"));
                dato.setFechaNacimiento(resultSet.getDate("fecha_nacimiento"));
                return dato;
            }
        }
        return null;
    }

}
