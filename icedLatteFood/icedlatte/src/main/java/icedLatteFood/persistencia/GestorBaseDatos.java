package persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;


public class GestorBaseDatos {
    private Connection connection;

    public GestorBaseDatos(String dbUrl) throws SQLException { //constructor
        this.connection = DriverManager.getConnection(dbUrl);
    }
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
        /*private boolean desconectar() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
            return true;
        } catch (SQLException e) {
            System.err.println("Error al desconectar de la base de datos: " + e.getMessage());
            return false;
        }
        }*/

        public int insert(String sql) {
            try (Statement statement = connection.createStatement()) {
                return statement.executeUpdate(sql);
            } catch (SQLException e) {
                System.err.println("Error al insertar en la base de datos: " + e.getMessage());
                return 0;
            }
        }
        public int update(String sql) {
            try (Statement statement = connection.createStatement()) {
                return statement.executeUpdate(sql);
            } catch (SQLException e) {
                System.err.println("Error al actualizar en la base de datos: " + e.getMessage());
                return 0;
            }
        }
        public int delete(String sql) {
            try (Statement statement = connection.createStatement()) {
                return statement.executeUpdate(sql);
            } catch (SQLException e) {
                System.err.println("Error al eliminar de la base de datos: " + e.getMessage());
                return 0;
            }
        }
        public ResultSet select(String sql) {
            try {
                Statement statement = connection.createStatement();
                return statement.executeQuery(sql);
            } catch (SQLException e) {
                System.err.println("Error al seleccionar de la base de datos: " + e.getMessage());
                return null;
            }
        }
        /*public void close() {
        desconectar();
        }*/
    }
}
