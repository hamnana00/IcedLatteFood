package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import dominio.entidades.ItemMenu;

public class ItemMenuDAO extends EntityDAO<ItemMenu> {

    // Constructor que recibe el gestor de base de datos
    public ItemMenuDAO(GestorBaseDatos gestorBD) {
        super(gestorBD);
    }

    // Método para insertar un ItemMenu
    @Override
    public int insert(ItemMenu item) {
        String sql = "INSERT INTO ItemMenu (id, nombre, precio, tipo) VALUES ('" +
                item.getId() + "', '" +
                item.getNombre() + "', " +
                item.getPrecio() + ", '" +
                item.getTipo() + "')";
        return gestorBD.insert(sql);
    }

    // Método para actualizar un ItemMenu
    @Override
    public int update(ItemMenu item) {
        String sql = "UPDATE ItemMenu SET nombre = '" + item.getNombre() +
                "', precio = " + item.getPrecio() +
                ", tipo = '" + item.getTipo() +
                "' WHERE id = '" + item.getId() + "'";
        return gestorBD.update(sql);
    }

    // Método para eliminar un ItemMenu
    @Override
    public int delete(ItemMenu item) {
        String sql = "DELETE FROM ItemMenu WHERE id = '" + item.getId() + "'";
        return gestorBD.delete(sql);
    }

    // Método para seleccionar un ItemMenu por su ID
    @Override
    public ItemMenu select(String id) {
        String sql = "SELECT * FROM ItemMenu WHERE id = '" + id + "'";
        ResultSet rs = gestorBD.select(sql);

        try {
            if (rs != null && rs.next()) {
                return new ItemMenu(
                        rs.getString("id"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        TipoItemMenu.valueOf(rs.getString("tipo"))
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al seleccionar el item: " + e.getMessage());
        }
        return null; // Si no se encuentra el item
    }
}
