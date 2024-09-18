package umg.progra2.DataBase.Dao;

import umg.progra2.DataBase.ConfigDB.DatabaseConnection;
import umg.progra2.DataBase.Model.EquipoChampions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EquipoChampionsDao {

    public void deleteEquipoChampionsById(int idEquipo) throws SQLException {
        String query = "DELETE FROM equipos_champions WHERE id_equipo = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idEquipo);
            statement.executeUpdate();
        }
    }

    public void updateEquipoChampions(EquipoChampions equipo) throws SQLException {
        String query = "UPDATE equipos_champions SET nombre = ?, pais = ?, ciudad = ?, estadio = ?, fundacion = ?, entrenador = ?, web_oficial = ?, facebook = ?, twitter = ?, instagram = ?, patrocinador_principal = ? WHERE id_equipo = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, equipo.getNombre());
            statement.setString(2, equipo.getPais());
            statement.setString(3, equipo.getCiudad());
            statement.setString(4, equipo.getEstadio());
            statement.setInt(5, equipo.getFundacion());
            statement.setString(6, equipo.getEntrenador());
            statement.setString(7, equipo.getWebOficial());
            statement.setString(8, equipo.getFacebook());
            statement.setString(9, equipo.getTwitter());
            statement.setString(10, equipo.getInstagram());
            statement.setString(11, equipo.getPatrocinadorPrincipal());
            statement.setInt(12, equipo.getIdEquipo());
            statement.executeUpdate();
        }
    }

    public void insertEquipoChampions(EquipoChampions equipo) throws SQLException {
        String query = "INSERT INTO equipos_champions (nombre, pais, ciudad, estadio, fundacion, entrenador, web_oficial, facebook, twitter, instagram, patrocinador_principal) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, equipo.getNombre());
            statement.setString(2, equipo.getPais());
            statement.setString(3, equipo.getCiudad());
            statement.setString(4, equipo.getEstadio());
            statement.setInt(5, equipo.getFundacion());
            statement.setString(6, equipo.getEntrenador());
            statement.setString(7, equipo.getWebOficial());
            statement.setString(8, equipo.getFacebook());
            statement.setString(9, equipo.getTwitter());
            statement.setString(10, equipo.getInstagram());
            statement.setString(11, equipo.getPatrocinadorPrincipal());
            statement.executeUpdate();
        }
    }

    public EquipoChampions getEquipoChampionsById(int idEquipo) throws SQLException {
        String query = "SELECT * FROM equipos_champions WHERE id_equipo = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idEquipo);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                EquipoChampions equipo = new EquipoChampions();
                equipo.setIdEquipo(resultSet.getInt("id_equipo"));
                equipo.setNombre(resultSet.getString("nombre"));
                equipo.setPais(resultSet.getString("pais"));
                equipo.setCiudad(resultSet.getString("ciudad"));
                equipo.setEstadio(resultSet.getString("estadio"));
                equipo.setFundacion(resultSet.getInt("fundacion"));
                equipo.setEntrenador(resultSet.getString("entrenador"));
                equipo.setWebOficial(resultSet.getString("web_oficial"));
                equipo.setFacebook(resultSet.getString("facebook"));
                equipo.setTwitter(resultSet.getString("twitter"));
                equipo.setInstagram(resultSet.getString("instagram"));
                equipo.setPatrocinadorPrincipal(resultSet.getString("patrocinador_principal"));
                equipo.setCreadoEn(resultSet.getTimestamp("creado_en"));
                return equipo;
            }
        }
        return null;
    }

}
