package persistencia;

import dominio.entidades.Restaurante;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RestauranteDAO extends EntityDAO<Restaurante> {

    // Constructor que recibe el gestor de base de datos
    public RestauranteDAO(GestorBaseDatos gestorBD) {
        super(gestorBD);
    }

    // Método para insertar un Restaurante
    @Override
    public int insert(Restaurante restaurante) {
        String sql = "INSERT INTO Restaurante (nombre, cif) VALUES ('" +
                restaurante.getNombre() + "', '" +
                restaurante.getCif() + "')";
        return gestorBD.insert(sql);
    }

    // Método para actualizar un Restaurante
    @Override
    public int update(Restaurante restaurante) {
        String sql = "UPDATE Restaurante SET nombre = '" + restaurante.getNombre() +
                "' WHERE cif = '" + restaurante.getCif() + "'";
        return gestorBD.update(sql);
    }

    // Método para eliminar un Restaurante
    @Override
    public int delete(Restaurante restaurante) {
        String sql = "DELETE FROM Restaurante WHERE cif = '" + restaurante.getCif() + "'";
        return gestorBD.delete(sql);
    }

    // Método para seleccionar un Restaurante por CIF
    @Override
    public Restaurante select(String cif) {
        String sql = "SELECT * FROM Restaurante WHERE cif = '" + cif + "'";
        ResultSet rs = gestorBD.select(sql);

        try {
            if (rs != null && rs.next()) {
                Restaurante restaurante = new Restaurante(
                        rs.getString("nombre"),
                        rs.getString("cif")
                );
                // Si hay un menú asociado o pedidos, deberías cargarlo también
                return restaurante;
            }
        } catch (SQLException e) {
            System.out.println("Error al seleccionar el restaurante: " + e.getMessage());
        }
        return null;
    }

    // Método para seleccionar restaurantes por código postal (basado en una consulta ficticia)
    public List<Restaurante> selectPorCodigoPostal(String codigoPostal) {
        String sql = "SELECT * FROM Restaurante WHERE codigo_postal = '" + codigoPostal + "'";
        ResultSet rs = gestorBD.select(sql);
        List<Restaurante> restaurantes = new ArrayList<>();

        try {
            while (rs != null && rs.next()) {
                Restaurante restaurante = new Restaurante(
                        rs.getString("nombre"),
                        rs.getString("cif")
                );
                restaurantes.add(restaurante);
            }
        } catch (SQLException e) {
            System.out.println("Error al seleccionar restaurantes: " + e.getMessage());
        }

        return restaurantes;
    }

    // Método para seleccionar por código postal y texto libre en nombre o menú
    public List<Restaurante> selectPorCodigoPostalYTextoLibre(String codigoPostal, String texto) {
        String sql = "SELECT * FROM Restaurante r JOIN Menu m ON r.cif = m.cif " +
                "WHERE r.codigo_postal = '" + codigoPostal + "' AND (r.nombre LIKE '%" + texto + "%' " +
                "OR m.nombre_item LIKE '%" + texto + "%')";
        ResultSet rs = gestorBD.select(sql);
        List<Restaurante> restaurantes = new ArrayList<>();

        try {
            while (rs != null && rs.next()) {
                Restaurante restaurante = new Restaurante(
                        rs.getString("nombre"),
                        rs.getString("cif")
                );
                restaurantes.add(restaurante);
            }
        } catch (SQLException e) {
            System.out.println("Error al seleccionar restaurantes: " + e.getMessage());
        }

        return restaurantes;
    }
}
