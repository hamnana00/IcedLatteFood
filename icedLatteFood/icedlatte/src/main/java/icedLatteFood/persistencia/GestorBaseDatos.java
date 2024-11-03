package icedLatteFood.persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;

public class GestorBaseDatos {
    private Connection connection; // Variable que almacena la conexión a la base de datos

    // Constructor que inicializa la conexión a la base de datos
    public GestorBaseDatos(String dbUrl) throws SQLException {
        this.connection = DriverManager.getConnection(dbUrl); // Establece la conexión utilizando el URL proporcionado
    }

    // Método para conectar a la base de datos (opcional si ya se conecta en el constructor)
    private boolean conectar() {
        try {
            if (connection == null || connection.isClosed()) {
                // Reemplaza con tus credenciales y URL de conexión
                String dbUrl = "jdbc:derby:memory:myDB;create=true"; // Cambia el URL según tu configuración
                connection = DriverManager.getConnection(dbUrl);
            }
            return true;
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
            return false;
        }
    }

    // Método para insertar un registro en la base de datos
    public int insert(String sql) {
        try (Statement statement = connection.createStatement()) {
            return statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.err.println("Error al insertar en la base de datos: " + e.getMessage());
            return 0;
        }
    }

    // Método para actualizar un registro en la base de datos
    public int update(String sql) {
        try (Statement statement = connection.createStatement()) {
            return statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.err.println("Error al actualizar en la base de datos: " + e.getMessage());
            return 0;
        }
    }

    // Método para eliminar un registro en la base de datos
    public int delete(String sql) {
        try (Statement statement = connection.createStatement()) {
            return statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.err.println("Error al eliminar de la base de datos: " + e.getMessage());
            return 0;
        }
    }

    // Método para seleccionar registros de la base de datos
    public ResultSet select(String sql) {
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            System.err.println("Error al seleccionar de la base de datos: " + e.getMessage());
            return null;
        }
    }

    // Método para obtener la conexión a la base de datos
    public Connection connect() {
        return connection; // Retorna la conexión establecida
    }

    // Método opcional para cerrar la conexión a la base de datos
    /*public void close() {
        desconectar();
    }*/
}
