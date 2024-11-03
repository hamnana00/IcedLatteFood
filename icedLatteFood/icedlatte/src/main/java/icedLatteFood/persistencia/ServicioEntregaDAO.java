package icedLatteFood.persistencia;

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
        return 0;
    }

    @Override
    public int update(ServicioEntrega entity) {
        return 0;
    }

    @Override
    public int delete(ServicioEntrega entity) {
        return 0;
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
