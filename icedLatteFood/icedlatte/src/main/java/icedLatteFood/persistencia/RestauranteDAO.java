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
        /*String sql = "INSERT INTO Restaurante (nombre, cif) VALUES ('" +
                restaurante.getNombre() + "', '" +
                restaurante.getCif() + "')";
        return gestorBD.insertarRestaurante(sql);*/
        try {
            gestorBD.insertarRestaurante(restaurante.getNombre(), restaurante.getCif());
            return 1;
        } catch (SQLException e) {
            System.out.println("Error al insertar el restaurante: " + e.getMessage());
            return 0;
        }
    }

    // Método para actualizar un Restaurante
    @Override
    /*public int update(Restaurante restaurante) {
        String sql = "UPDATE Restaurante SET nombre = '" + restaurante.getNombre() +
                "' WHERE cif = '" + restaurante.getCif() + "'";
        return gestorBD.actualizarRestaurante(restaurante.getNombre(), restaurante.getCif());
    }*/

    // Método para eliminar un Restaurante
    @Override
    public int delete(Restaurante restaurante) {
        String sql = "DELETE FROM Restaurante WHERE cif = '" + restaurante.getCif() + "'";
        return gestorBD.eliminarRestaurante(restaurante.getCif());
    }

    // Método para seleccionar un Restaurante por CIF
    @Override
    public Restaurante select(String cif) {
        String sql = "SELECT * FROM Restaurante WHERE cif = '" + cif + "'";
        ResultSet rs = gestorBD.seleccionarPorCif(cif);

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
        ResultSet rs = gestorBD.select(codigoPostal);
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

    // Método para seleccionar por nombre
    public List<Restaurante> selectPorNombre(String texto) {
        String sql = "SELECT * FROM Restaurante r JOIN Menu m ON r.cif = m.cif " +
                "WHERE (r.nombre LIKE '%" + texto + "%' " +
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

    public List<Restaurante> selectAll() {
        String sql = "SELECT * FROM Restaurante";
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
            System.out.println("Error al seleccionar todos los restaurantes: " + e.getMessage());
        }

        return restaurantes;
    }

}
