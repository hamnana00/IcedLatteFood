package persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GestorBaseDatos {
    private Connection connection;

    public GestorBaseDatos(Connection connection) {
        this.connection = connection;
    }

    // Método para insertar un restaurante en la base de datos
    public void insertarRestaurante(String nombre, String cif) throws SQLException {
        String sql = "INSERT INTO Restaurante (nombre, cif, favorito, direccion, codigopostal) VALUES ('"
                + nombre + "', '" + cif + "', '"+favorito+"', '"+direccion+"', '"+codigopostal+"')";
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
    }
    public void actualizarRestaurante(String cif, String nuevoNombre) throws SQLException {
        String sql = "UPDATE Restaurante SET nombre = '" + nuevoNombre + "' WHERE cif = '" + cif + "'";
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
    }
    public void eliminarRestaurante(String cif) throws SQLException {
        String sql = "DELETE FROM Restaurante WHERE cif = '" + cif + "'";
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
    }

    // Método para seleccionar todos los restaurantes
    public void mostrarRestaurantes() throws SQLException {
        String sql = "SELECT * FROM Restaurante";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            System.out.println("Nombre: " + resultSet.getString("nombre") + ", CIF: " + resultSet.getString("cif"));
        }
    }
    public void seleccionarPorCif(String cif) throws SQLException {
        String sql = "SELECT * FROM Restaurante WHERE cif = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, cif);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Crear un objeto Restaurante a partir de los datos obtenidos
                return new Restaurante(
                        resultSet.getString("nombre"),
                        resultSet.getString("cif")
                );
            }
        }
        return null; // Retorna null si no se encuentra el restaurante
    }
}
