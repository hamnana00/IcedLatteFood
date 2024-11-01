package persistencia;

import dominio.entidades.Restaurante;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;


public class RestauranteDAO extends EntityDAO<Restaurante> {
    public boolean agregarRestaurante(String nombre, String cif) {
        String sql = "INSERT INTO Restaurante (nombre, cif) VALUES (?, ?)";
        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.setString(2, cif);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public Restaurante obtenerRestaurante(int id) {
        String sql = "SELECT * FROM Restaurante WHERE id = ?";
        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Restaurante(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("cif"),
                        rs.getBoolean("favorito")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Restaurante selectPorCodigoPostal(int codigoPostal) {
        String sql = "SELECT * FROM Restaurante WHERE codigoPostal = ?";
        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, codigoPostal);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Restaurante(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("cif"),
                        rs.getBoolean("favorito")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Restaurante selectPorNombre(int nombre) {
        String sql = "SELECT * FROM Restaurante WHERE nombre = ?";
        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, nombre);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Restaurante(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("cif"),
                        rs.getBoolean("favorito")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Restaurante> obtenerTodosLosRestaurantes() {
        List<Restaurante> restaurantes = new ArrayList<>();
        String sql = "SELECT * FROM Restaurante";
        try (Connection connection = DatabaseConnection.connect();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                restaurantes.add(new Restaurante(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("cif"),
                        rs.getBoolean("favorito")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return restaurantes;
    }
    public boolean actualizarRestaurante(int id, String nombre, String cif, boolean favorito) {
        String sql = "UPDATE Restaurante SET nombre = ?, cif = ?, favorito = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.setString(2, cif);
            stmt.setBoolean(3, favorito);
            stmt.setInt(4, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean eliminarRestaurante(int id) {
        String sql = "DELETE FROM Restaurante WHERE id = ?";
        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean marcarFavorito(int id, boolean favorito) {
        String sql = "UPDATE Restaurante SET favorito = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setBoolean(1, favorito);
            stmt.setInt(2, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
