package persistencia;

import dominio.entidades.ServicioEntrega;
import java.sql.*;

public class ServicioEntregaDAO extends EntityDAO<ServicioEntrega> {

    public ServicioEntregaDAO(GestorBaseDatos gestorBD) {
        super(gestorBD);
    }

    @Override
    public int insert(ServicioEntrega entity) throws SQLException {
        String sql = "INSERT INTO servicio_entrega (pedido_id, direccion_id, repartidor_id, fecha_recepcion, fecha_entrega) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = gestorBD.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, entity.getPedido().getId()); // Asumiendo que Pedido tiene un método getId()
            stmt.setInt(2, entity.getDireccion().getId()); // Asumiendo que Direccion tiene un método getId()
            stmt.setInt(3, entity.getRepartidor().getId()); // Asumiendo que Repartidor tiene un método getId()
            stmt.setTimestamp(4, entity.getFechaRecepcion() != null ? Timestamp.valueOf(entity.getFechaRecepcion()) : null);
            stmt.setTimestamp(5, entity.getFechaEntrega() != null ? Timestamp.valueOf(entity.getFechaEntrega()) : null);
            stmt.executeUpdate();

            // Obtiene el ID generado
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); // Retorna el ID del servicio de entrega insertado
            }
            return -1; // Si no se obtuvo un ID
        }
    }

    @Override
    public int update(ServicioEntrega entity) throws SQLException {
        String sql = "UPDATE servicio_entrega SET pedido_id = ?, direccion_id = ?, repartidor_id = ?, fecha_recepcion = ?, fecha_entrega = ? WHERE id = ?";
        try (PreparedStatement stmt = gestorBD.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, entity.getPedido().getId());
            stmt.setInt(2, entity.getDireccion().getId());
            stmt.setInt(3, entity.getRepartidor().getId());
            stmt.setTimestamp(4, entity.getFechaRecepcion() != null ? Timestamp.valueOf(entity.getFechaRecepcion()) : null);
            stmt.setTimestamp(5, entity.getFechaEntrega() != null ? Timestamp.valueOf(entity.getFechaEntrega()) : null);
            stmt.setInt(6, entity.getId()); // Asumiendo que ServicioEntrega tiene un método getId()
            return stmt.executeUpdate(); // Retorna el número de filas afectadas
        }
    }

    @Override
    public int delete(ServicioEntrega entity) throws SQLException {
        String sql = "DELETE FROM servicio_entrega WHERE id = ?";
        try (PreparedStatement stmt = gestorBD.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, entity.getId()); // Asumiendo que ServicioEntrega tiene un método getId()
            return stmt.executeUpdate(); // Retorna el número de filas afectadas
        }
    }

    @Override
    public ServicioEntrega select(String id) throws SQLException {
        String sql = "SELECT * FROM servicio_entrega WHERE id = ?";
        try (PreparedStatement stmt = gestorBD.getConnection().prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Crea y retorna un objeto ServicioEntrega a partir del ResultSet
                ServicioEntrega servicioEntrega = new ServicioEntrega(/* parámetros necesarios */);
                servicioEntrega.setFechaRecepcion(rs.getTimestamp("fecha_recepcion").toLocalDateTime());
                servicioEntrega.setFechaEntrega(rs.getTimestamp("fecha_entrega").toLocalDateTime());
                // Aquí deberías asignar el ID al servicio de entrega
                servicioEntrega.setId(rs.getInt("id")); // Asumiendo que ServicioEntrega tiene un método setId()
                return servicioEntrega;
            }
            return null; // Retorna null si no se encontró el servicio de entrega
        }
    }
}
