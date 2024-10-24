package persistencia;

import dominio.entidades.Pedido;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO extends EntityDAO<Pedido> {

    public PedidoDAO(GestorBaseDatos gestorBD) {
        super(gestorBD);
    }

    @Override
    public int insert(Pedido entity) throws SQLException {
        String sql = "INSERT INTO pedidos (fecha, estado) VALUES (?, ?)";
        try (PreparedStatement stmt = gestorBD.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setDate(1, new java.sql.Date(entity.getFecha().getTime())); // Convierte Date a SQL Date
            stmt.setString(2, entity.getEstado().name());
            stmt.executeUpdate();

            // Obtiene el ID generado
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); // Retorna el ID del pedido insertado
            }
            return -1; // Si no se obtuvo un ID
        }
    }

    @Override
    public int update(Pedido entity) throws SQLException {
        String sql = "UPDATE pedidos SET fecha = ?, estado = ? WHERE id = ?";
        try (PreparedStatement stmt = gestorBD.getConnection().prepareStatement(sql)) {
            stmt.setDate(1, new java.sql.Date(entity.getFecha().getTime()));
            stmt.setString(2, entity.getEstado().name());
            stmt.setInt(3, entity.getId()); // Asumiendo que Pedido tiene un método getId()
            return stmt.executeUpdate(); // Retorna el número de filas afectadas
        }
    }

    @Override
    public int delete(Pedido entity) throws SQLException {
        String sql = "DELETE FROM pedidos WHERE id = ?";
        try (PreparedStatement stmt = gestorBD.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, entity.getId()); // Asumiendo que Pedido tiene un método getId()
            return stmt.executeUpdate(); // Retorna el número de filas afectadas
        }
    }

    @Override
    public Pedido select(String id) throws SQLException {
        String sql = "SELECT * FROM pedidos WHERE id = ?";
        try (PreparedStatement stmt = gestorBD.getConnection().prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Crea y retorna un objeto Pedido a partir del ResultSet
                Pedido pedido = new Pedido(rs.getDate("fecha"));
                pedido.setEstado(Pedido.Estado.valueOf(rs.getString("estado")));
                // Aquí deberías asignar el ID al pedido
                pedido.setId(rs.getInt("id")); // Asumiendo que Pedido tiene un método setId()
                return pedido;
            }
            return null; // Retorna null si no se encontró el pedido
        }
    }
}
