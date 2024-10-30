package persistencia;

import dominio.entidades.Pedido;
import java.sql.*;

public class PedidoDAO {
    private GestorBaseDatos gestorBD;

    public PedidoDAO(GestorBaseDatos gestorBD) {
        this.gestorBD = gestorBD;
    }

    public int insert(Pedido pedido) {
        String sql = "INSERT INTO Pedido (fecha, cliente_id, restaurante_id, estado) VALUES (?, ?, ?, ?)";
        try (Connection conn = gestorBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setDate(1, new java.sql.Date(pedido.getFecha().getTime()));
            stmt.setInt(2, pedido.getCliente().getId());
            stmt.setInt(3, pedido.getRestaurante().getId());
            stmt.setString(4, pedido.getEstado().toString());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al insertar pedido: " + e.getMessage());
        }
        return -1; // Retorna -1 si no se generó un ID
    }

    public void update(Pedido pedido) {
        String sql = "UPDATE Pedido SET estado = ?, pago_id = ? WHERE id = ?";
        try (Connection conn = gestorBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, pedido.getEstado().toString());
            stmt.setInt(2, pedido.getPago().getId()); // suponiendo que Pago tiene un ID
            stmt.setInt(3, pedido.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar pedido: " + e.getMessage());
        }
    }
}
