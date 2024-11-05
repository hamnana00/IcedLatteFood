package icedLatteFood.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import icedLatteFood.dominio.entidades.Direccion;
import icedLatteFood.dominio.entidades.Restaurante;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import icedLatteFood.dominio.entidades.Cliente;
import icedLatteFood.persistencia.DatabaseConnection;

public class ClienteDAO { //extender a crudRepository <nombre, log>
    private Connection connection;

    // Constructor que recibe la conexión a la base de datos
    public ClienteDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para insertar un cliente
    public int insert(Cliente cliente) {
        String sql = "INSERT INTO clientes (nombre, apellidos, dni) VALUES (?, ?, ?)"; // Asegúrate de que los nombres de las columnas coincidan con tu esquema
        int idGenerado = -1;

        try (PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellidos());
            stmt.setString(3, cliente.getDni()); // Asumimos que tienes un campo para el DNI

            // Ejecutar la inserción
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        idGenerado = generatedKeys.getInt(1); // Obtener el ID generado
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al insertar el cliente: " + e.getMessage());
        }

        return idGenerado; // Retornar el ID generado o -1 si hubo un error
    }
}