package icedLatteFood.persistencia;

import icedLatteFood.dominio.entidades.Direccion;
import icedLatteFood.dominio.entidades.Restaurante;
import icedLatteFood.dominio.entidades.ServicioEntrega;
import icedLatteFood.dominio.entidades.Pedido;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicioEntregaDAO extends EntityDAO<ServicioEntrega> {

    // Constructor que recibe GestorBaseDatos
    public ServicioEntregaDAO(GestorBaseDatos gestorBD) {
        super(gestorBD); // Llama al constructor de la clase base
    }

    @Override
    public int insert(ServicioEntrega entity) {
        String sql = "INSERT INTO ServicioEntrega (pedidoId, direccionId, repartidorId, fechaRecepcion, fechaEntrega) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Suponiendo que `entity` tiene métodos para obtener sus atributos
            stmt.setInt(1, entity.getPedido().getId()); // ID del pedido
            stmt.setInt(2, entity.getDireccion().getNumero()); // ID de la dirección
            stmt.setInt(3, entity.getRepartidorId()); // ID del repartidor
            stmt.setTimestamp(4, Timestamp.valueOf(entity.getFechaRecepcion())); // Fecha de recepción
            stmt.setTimestamp(5, Timestamp.valueOf(entity.getFechaEntrega())); // Fecha de entrega

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1); // Retorna el ID generado
                    } else {
                        throw new SQLException("No se pudo obtener el ID generado.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Retorna -1 si falla
    }

    @Override
    public int update(ServicioEntrega entity) {
        String sql = "UPDATE ServicioEntrega SET pedidoId = ?, direccionId = ?, repartidorId = ?, fechaRecepcion = ?, fechaEntrega = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, entity.getPedido().getId());
            stmt.setInt(2, entity.getDireccion().getNumero());
            stmt.setInt(3, entity.getRepartidorId());
            stmt.setTimestamp(4, Timestamp.valueOf(entity.getFechaRecepcion()));
            stmt.setTimestamp(5, Timestamp.valueOf(entity.getFechaEntrega()));

            return stmt.executeUpdate(); // Retorna el número de filas afectadas
        } catch (SQLException e) {
            e.printStackTrace();
            return 0; // Retorna 0 si ocurre un error
        }
    }

    @Override
    public int delete(ServicioEntrega entity) {
        String sql = "DELETE FROM ServicioEntrega WHERE id = ?";
        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            return stmt.executeUpdate(); // Retorna el número de filas afectadas
        } catch (SQLException e) {
            e.printStackTrace();
            return 0; // Retorna 0 si ocurre un error
        }
    }

    @Override
    public ServicioEntrega select(String id) {
        return null;
    }
    @Override
    public List<Restaurante> selectPorCodigoPostal(String s) {
        return List.of();
    }

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
                        rs.getString("idCli"),
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
