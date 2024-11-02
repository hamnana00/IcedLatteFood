package icedLatteFood.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import icedLatteFood.dominio.entidades.Restaurante;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import icedLatteFood.dominio.entidades.Cliente;
import icedLatteFood.persistencia.DatabaseConnection;

public class ClienteDAO {
    public boolean agregarCliente(String nombre, String apellido1, String apellido2) {
        String sql = "INSERT INTO Cliente (nombre, apellido1, apellido2) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.setString(2, apellido1);
            stmt.setString(3, apellido2);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public Cliente obtenerCliente(int idCli) {
        String sql = "SELECT * FROM Cliente WHERE idCli = ?";
        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idCli);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Cliente(
                        rs.getInt("idCli"),
                        rs.getString("nombre"),
                        rs.getString("apellido1"),
                        rs.getString("apellido2")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean actualizarCliente(int idCli, String nombre, String apellido1, String apellido2) {
        String sql = "UPDATE Cliente SET nombre = ?, apellido1 = ?, apellido2 = ? WHERE idCli = ?";
        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.setString(2, apellido1);
            stmt.setString(3, apellido2);
            stmt.setInt(4, idCli);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean eliminarCliente(int idCli) {
        String sql = "DELETE FROM Cliente WHERE idCli = ?";
        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idCli);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}