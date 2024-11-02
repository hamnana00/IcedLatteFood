package icedLatteFood.persistencia;

import icedLatteFood.dominio.entidades.Pedido;
import icedLatteFood.persistencia.EntityDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO extends EntityDAO<Pedido> {
    public boolean agregarPedido(int idCli, String nombre, String origen, int destino, double precioTotal, Time hora, int idRepar) {
        String sql = "INSERT INTO Pedido (idCli, nombre, origen, destino, precioTotal, hora, idRepar) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = icedLatteFood.persistencia.DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idCli);
            stmt.setString(2, nombre);
            stmt.setString(3, origen);
            stmt.setInt(4, destino);
            stmt.setDouble(5, precioTotal);
            stmt.setTime(6, hora);
            stmt.setInt(7, idRepar);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public Pedido obtenerPedido(int idPed) {
        String sql = "SELECT * FROM Pedido WHERE idPed = ?";
        try (Connection connection = icedLatteFood.persistencia.DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idPed);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Pedido(
                        rs.getInt("idPed"),
                        rs.getInt("idCli"),
                        rs.getString("nombre"),
                        rs.getString("origen"),
                        rs.getInt("destino"),
                        rs.getDouble("precioTotal"),
                        rs.getTime("hora"),
                        rs.getInt("idRepar")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean actualizarPedido(int idPed, String nombre, String origen, int destino, double precioTotal, Time hora, int idRepar) {
        String sql = "UPDATE Pedido SET nombre = ?, origen = ?, destino = ?, precioTotal = ?, hora = ?, idRepar = ? WHERE idPed = ?";
        try (Connection connection = persistencia.DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.setString(2, origen);
            stmt.setInt(3, destino);
            stmt.setDouble(4, precioTotal);
            stmt.setTime(5, hora);
            stmt.setInt(6, idRepar);
            stmt.setInt(7, idPed);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean eliminarPedido(int idPed) {
        String sql = "DELETE FROM Pedido WHERE idPed = ?";
        try (Connection connection = icedLatteFood.persistencia.DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idPed);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
