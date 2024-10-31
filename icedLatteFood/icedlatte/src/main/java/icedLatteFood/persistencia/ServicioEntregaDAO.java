package persistencia;

import dominio.entidades.ServicioEntrega;
import java.sql.*;

public class ServicioEntregaDAO extends EntityDAO<ServicioEntrega> {
    public boolean marcarPedidoEntregado(int idPed) {
        String sql = "UPDATE Pedido SET estado = 'ENTREGADO' WHERE idPed = ?";
        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idPed);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<Pedido> obtenerPedidosPendientes() {
        List<Pedido> pedidosPendientes = new ArrayList<>();
        String sql = "SELECT * FROM Pedido WHERE estado != 'ENTREGADO'";
        try (Connection connection = DatabaseConnection.connect();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                pedidosPendientes.add(new Pedido(
                        rs.getInt("idPed"),
                        rs.getInt("idCli"),
                        rs.getString("nombre"),
                        rs.getString("origen"),
                        rs.getInt("destino"),
                        rs.getDouble("precioTotal"),
                        rs.getTime("hora"),
                        rs.getInt("idRepar")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedidosPendientes;
    }
}
