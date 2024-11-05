package icedLatteFood.persistencia;

import java.sql.*;
import java.sql.Connection;
import icedLatteFood.dominio.entidades.ItemMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public abstract class ItemMenuDAO extends EntityDAO<ItemMenu> {


    public ItemMenuDAO(GestorBaseDatos gestorBD) {
        super(gestorBD);
    }

    public boolean agregarItemMenu(int idRest, String idItem, String nombre, double precio) {
        String sql = "INSERT INTO Menu (idRest, idItem, nombre, precio) VALUES (?, ?, ?, ?)";
        try (Connection connection = icedLatteFood.persistencia.DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idRest);
            stmt.setString(2, idItem);
            stmt.setString(3, nombre);
            stmt.setDouble(4, precio);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ItemMenu obtenerItemMenu(String idItem) {
        String sql = "SELECT * FROM Menu WHERE idItem = ?";
        try (Connection connection = icedLatteFood.persistencia.DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, idItem);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new ItemMenu(
                        rs.getString("idItem"),
                        rs.getDouble("precio")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean actualizarItemMenu(String idItem, String nombre, double precio) {
        String sql = "UPDATE Menu SET nombre = ?, precio = ? WHERE idItem = ?";
        try (Connection connection = icedLatteFood.persistencia.DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.setDouble(2, precio);
            stmt.setString(3, idItem);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarItemMenu(String idItem) {
        String sql = "DELETE FROM Menu WHERE idItem = ?";
        try (Connection connection = icedLatteFood.persistencia.DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, idItem);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
