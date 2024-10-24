package persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GestorBaseDatos {
    private Connection connection;

    public GestorBaseDatos() {
        this.connection = DatabaseConnection.connect(); // Conecta a la base de datos
    }

    public boolean conectar() {
        return connection != null; // Retorna true si ya está conectado
    }

    public boolean desconectar() {
        DatabaseConnection.disconnect(); // Desconecta utilizando la clase DatabaseConnection
        return true; // Siempre retorna true ya que el método disconnect maneja los errores
    }

    public int insert(String sql) {
        return ejecutarModificacion(sql);
    }

    public int update(String sql) {
        return ejecutarModificacion(sql);
    }

    public int delete(String sql) {
        return ejecutarModificacion(sql);
    }

    public ResultSet select(String sql) {
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la consulta: " + e.getMessage());
            return null;
        }
    }

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
