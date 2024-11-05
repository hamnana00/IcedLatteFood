package icedLatteFood.persistencia;

import icedLatteFood.dominio.entidades.Pedido;
import icedLatteFood.dominio.entidades.Restaurante;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// Clase que maneja las operaciones de base de datos para la entidad Pedido
public interface PedidoDAO extends JpaRepository<Pedido, Integer> {

    // Constructor que recibe el gestor de base de datos
    /*public PedidoDAO(GestorBaseDatos gestorBD) {
        super(gestorBD); // Llama al constructor de la clase padre
    }*/

    // Método para agregar un nuevo pedido
    public boolean agregarPedido(Pedido pedido) {
        String sql = "INSERT INTO Pedido (idCli, nombre, origen, destino, precioTotal, hora, idRepar) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = gestorBD.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            // Establecer los parámetros del PreparedStatement desde el objeto Pedido
            stmt.setString(1, pedido.getCliente().getIdUsuario()); // Asumiendo que Pedido tiene un método getCliente()
            stmt.setString(2, pedido.getNombre()); // Método getNombre() en Pedido
            stmt.setString(3, pedido.getOrigen()); // Método getOrigen() en Pedido
            stmt.setInt(4, Integer.parseInt(pedido.getDestino())); // Método getDestino() en Pedido
            stmt.setDouble(5, pedido.getPrecioTotal()); // Método getPrecioTotal() en Pedido
            stmt.setTime(6, Time.valueOf(pedido.getHora().toString())); // Asegúrate de que el formato sea 'HH:MM:SS'
            stmt.setInt(7, pedido.getRepartidorId()); // Método getRepartidorId() en Pedido

            return stmt.executeUpdate() > 0; // Retorna true si se inserta correctamente
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Retorna false en caso de error
        }
    }

    // Método para obtener un pedido por ID
    public Pedido obtenerPedido(int idPed) {
        String sql = "SELECT * FROM Pedido WHERE idPed = ?";
        try (Connection connection = gestorBD.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idPed);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Asegúrate de que los nombres de las columnas coincidan con tu base de datos
                return new Pedido(
                        rs.getInt("idPed"),
                        rs.getString("idCli"),
                        rs.getString("nombre"),
                        rs.getString("origen"),
                        rs.getInt("destino"),
                        rs.getDouble("precioTotal"),
                        rs.getTime("hora"), // Cambiar a getTime si es necesario
                        rs.getInt("idRepar")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Retorna null si no se encuentra el pedido
    }

    // Método para actualizar un pedido existente
    public boolean actualizarPedido(Pedido pedido) {
        String sql = "UPDATE Pedido SET nombre = ?, origen = ?, destino = ?, precioTotal = ?, hora = ?, idRepar = ? WHERE idPed = ?";
        try (Connection connection = gestorBD.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, pedido.getNombre());
            stmt.setString(2, pedido.getOrigen());
            stmt.setInt(3, Integer.parseInt(pedido.getDestino()));
            stmt.setDouble(4, pedido.getPrecioTotal());
            stmt.setTime(5, Time.valueOf(pedido.getHora().toString())); // Asegúrate de que el formato sea 'HH:MM:SS'
            stmt.setInt(6, pedido.getRepartidorId());
            stmt.setInt(7, pedido.getId()); // Método getId() en Pedido

            return stmt.executeUpdate() > 0; // Retorna true si se actualiza correctamente
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Retorna false en caso de error
        }
    }

    // Método para eliminar un pedido por su ID
    public boolean eliminarPedido(int idPed) {
        String sql = "DELETE FROM Pedido WHERE idPed = ?";
        try (Connection connection = gestorBD.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idPed);
            return stmt.executeUpdate() > 0; // Retorna true si se elimina correctamente
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Retorna false en caso de error
        }
    }

    // Implementación del método abstracto para insertar
    @Override
    public int insert(Pedido pedido) {
        return agregarPedido(pedido) ? 1 : 0; // Asume que el insert devuelve un id o 0 si falla
    }

    // Implementación del método abstracto para actualizar
    @Override
    public int update(Pedido pedido) {
        return actualizarPedido(pedido) ? 1 : 0; // Asume que el update devuelve 1 si tiene éxito
    }

    // Implementación del método abstracto para eliminar
    @Override
    public int delete(Pedido pedido) {
        return eliminarPedido(pedido.getId()) ? 1 : 0; // Asume que el delete devuelve 1 si tiene éxito
    }

    // Implementación del método abstracto para seleccionar un pedido por su ID
    @Override
    public Pedido select(String id) {
        return obtenerPedido(Integer.parseInt(id)); // Convierte el id a int
    }

    // Implementación del método abstracto para seleccionar por código postal (puede que no sea relevante aquí)
    @Override
    public List<Restaurante> selectPorCodigoPostal(String s) {
        return new ArrayList<>(); // Retorna una lista vacía o implementar la lógica según sea necesario
    }
}
