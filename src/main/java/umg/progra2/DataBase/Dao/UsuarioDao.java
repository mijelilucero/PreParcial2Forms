package umg.progra2.DataBase.Dao;

import umg.progra2.DataBase.ConfigDB.DatabaseConnection;
import umg.progra2.DataBase.Model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDao {

    public void deleteUserByEmail(String email) throws SQLException {
        String query = "DELETE FROM tb_usuarios WHERE correo = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            statement.executeUpdate();
        }
    }


    public void updateUser(Usuario user) throws SQLException {
        String query = "UPDATE tb_usuarios SET carne = ?, nombre = ?, correo = ?, seccion = ?, telegramid = ?, activo = ? WHERE idusuario = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getCarne());
            statement.setString(2, user.getNombre());
            statement.setString(3, user.getCorreo());
            statement.setString(4, user.getSeccion());
            statement.setLong(5, user.getTelegramid());
            statement.setInt(6, user.getActivo());
            statement.setInt(7, user.getId());
            statement.executeUpdate();
        }
    }


    public void insertUser(Usuario user) throws SQLException {
        String query = "INSERT INTO tb_usuarios (carne, nombre, correo, seccion, telegramid, activo) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getCarne());
            statement.setString(2, user.getNombre());
            statement.setString(3, user.getCorreo());
            statement.setString(4, user.getSeccion());
            statement.setLong(5, user.getTelegramid());
            statement.setInt(6, user.getActivo());
            statement.executeUpdate();
        }
    }


    public Usuario getUserByTelegramId(long telegramid) throws SQLException {
        String query = "SELECT * FROM tb_usuarios WHERE telegramid = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, telegramid);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Usuario user = new Usuario();
                user.setId(resultSet.getInt("idusuario"));
                user.setCarne(resultSet.getString("carne"));
                user.setNombre(resultSet.getString("nombre"));
                user.setCorreo(resultSet.getString("correo"));
                user.setSeccion(resultSet.getString("seccion"));
                user.setTelegramid(resultSet.getLong("telegramid"));
                user.setActivo(resultSet.getInt("activo"));
                return user;
            }
        }
        return null;
    }


    public Usuario getUserByEmail(String Email) throws SQLException {
        String query = "SELECT * FROM tb_usuarios WHERE correo = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, Email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Usuario user = new Usuario();
                user.setId(resultSet.getInt("idusuario"));
                user.setCarne(resultSet.getString("carne"));
                user.setNombre(resultSet.getString("nombre"));
                user.setCorreo(resultSet.getString("correo"));
                user.setSeccion(resultSet.getString("seccion"));
                user.setTelegramid(resultSet.getLong("telegramid"));
                user.setActivo(resultSet.getInt("activo"));
                return user;
            }
        }
        return null;
    }


    //Nueva Consulta
    public Usuario getUserByCarne(String Carne) throws SQLException {
        String query = "SELECT * FROM tb_usuarios WHERE carne = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, Carne);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Usuario user = new Usuario();
                user.setId(resultSet.getInt("idusuario"));
                user.setCarne(resultSet.getString("carne"));
                user.setNombre(resultSet.getString("nombre"));
                user.setCorreo(resultSet.getString("correo"));
                user.setSeccion(resultSet.getString("seccion"));
                user.setTelegramid(resultSet.getLong("telegramid"));
                user.setActivo(resultSet.getInt("activo"));
                return user;
            }
        }
        return null;
    }

}
