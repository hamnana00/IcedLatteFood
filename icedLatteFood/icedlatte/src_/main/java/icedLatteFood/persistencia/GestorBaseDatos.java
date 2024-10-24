package persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RestauranteDAO {
    private Connection connection;

    public RestauranteDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para insertar un restaurante en la base de datos
    public void insertarRestaurante(String nombre, String cif) throws SQLException {
        String sql = "INSERT INTO Restaurante (nombre, cif) VALUES ('" + nombre + "', '" + cif + "')";
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
}
