package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GestorBaseDatos {
    private Connection connection;

    // Método para conectar a la base de datos
    public boolean conectar() {
        try {
            // Por ejemplo, con una base de datos MySQL
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/nombreBD", "usuario", "contraseña");
            return true;
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            return false;
        }
    }

    // Método para desconectar de la base de datos
    public boolean desconectar() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
            return true;
        } catch (SQLException e) {
            System.out.println("Error al desconectar de la base de datos: " + e.getMessage());
            return false;
        }
    }

    // Método para ejecutar una inserción
    public int insert(String sql) {
        return ejecutarModificacion(sql);
    }

    // Método para ejecutar una actualización
    public int update(String sql) {
        return ejecutarModificacion(sql);
    }

    // Método para ejecutar una eliminación
    public int delete(String sql) {
        return ejecutarModificacion(sql);
    }

    // Método para seleccionar datos
    public ResultSet select(String sql) {
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la consulta: " + e.getMessage());
            return null;
        }
    }

    // Método auxiliar para ejecutar INSERT, UPDATE, DELETE
    private int ejecutarModificacion(String sql) {
        try {
            Statement statement = connection.createStatement();
            return statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la modificación: " + e.getMessage());
            return -1;
        }
    }
}
