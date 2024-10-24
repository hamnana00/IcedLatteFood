package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:derby:memory:myDB;create=true"; // Nombre de la base de datos
    private static final String USER = ""; // Usuario (vacío para Derby)
    private static final String PASSWORD = ""; // Contraseña (vacío para Derby)

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) {
        try (Connection connection = connect()) {
            System.out.println("Conexión exitosa a la base de datos Derby.");
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
    }
}
