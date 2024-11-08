package icedLatteFood.persistencia;

import icedLatteFood.dominio.entidades.Restaurante;
import icedLatteFood.dominio.entidades.Direccion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import icedLatteFood.*;

@Repository
public interface RestauranteDAO extends JpaRepository <Restaurante, String> {
    List<Restaurante> findAllRestaurantes();
    List<Restaurante> findByNombreContaining(String nombre);
    List<Restaurante> findByCodigoPostal(String codigoPostal);
    Optional<Restaurante> findById(String idUsuario);
}
/*public interface RestauranteDAO extends JpaRepository<Restaurante, String>{

    /*public RestauranteDAO(GestorBaseDatos gestorBD) {
        super(gestorBD);
    }

    // Agregar restaurante con nombre, cif y dirección
    public Restaurante obtenerRestaurante(int id) {
        String sql = "SELECT * FROM Restaurante WHERE id = ?";
        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Crear el objeto Direccion
                Direccion direccion = new Direccion(
                        rs.getString("calle"),
                        rs.getInt("numero"),
                        rs.getString("complemento"),
                        rs.getInt("codigoPostal"),
                        rs.getString("municipio")
                );
                // Convertir "favorito" manualmente
                boolean favorito = rs.getInt("favorito") == 1; // Cambia aquí

                return new Restaurante(
                        rs.getString("pass"),
                        rs.getString("nombre"),
                        rs.getString("cif"),
                        favorito,
                        direccion
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    // Método para buscar restaurantes por código postal
    public List<Restaurante> selectPorCodigoPostal(int codigoPostal) {
        List<Restaurante> restaurantes = new ArrayList<>();
        String sql = "SELECT * FROM Restaurante WHERE codigoPostal = ?";
        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, codigoPostal);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // Crear el objeto Direccion
                Direccion direccion = new Direccion(
                        rs.getString("calle"),
                        rs.getInt("numero"),
                        rs.getString("complemento"),
                        rs.getInt("codigoPostal"),
                        rs.getString("municipio")
                );
                // Convertir "favorito" manualmente
                boolean favorito = rs.getInt("favorito") == 1; // Cambia aquí
                restaurantes.add(new Restaurante(
                        rs.getString("pass"),
                        rs.getString("nombre"),
                        rs.getString("cif"),
                        favorito,
                        direccion
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return restaurantes;
    }

    // Seleccionar todos los restaurantes
    public List<Restaurante> selectAll() {
        List<Restaurante> restaurantes = new ArrayList<>();
        String sql = "SELECT * FROM Restaurante";
        try (Connection connection = DatabaseConnection.connect();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Direccion direccion = new Direccion(
                        rs.getString("calle"),
                        rs.getInt("numero"),
                        rs.getString("complemento"),
                        rs.getInt("codigoPostal"),
                        rs.getString("municipio")
                );
                restaurantes.add(new Restaurante(
                        rs.getString("pass"),
                        rs.getString("nombre"),
                        rs.getString("cif"),
                        rs.getBoolean("favorito"),
                        direccion
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return restaurantes;
    }

    public List<Restaurante> selectPorCodigoPostalYTextoLibre(String codigoPostal, String texto) {
        List<Restaurante> restaurantes = new ArrayList<>();

        // Consulta SQL que busca restaurantes por código postal y texto en nombre o cif
        String sql = "SELECT * FROM Restaurante WHERE codigoPostal = ? AND (nombre LIKE ? OR cif LIKE ?)";

        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            // Configuración de los parámetros para la consulta
            stmt.setString(1, codigoPostal); // Código postal exacto
            stmt.setString(2, "%" + texto + "%"); // Búsqueda parcial en el nombre
            stmt.setString(3, "%" + texto + "%"); // Búsqueda parcial en el cif

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // Crear el objeto Direccion
                Direccion direccion = new Direccion(
                        rs.getString("calle"),
                        rs.getInt("numero"),
                        rs.getString("complemento"),
                        rs.getInt("codigoPostal"),
                        rs.getString("municipio")
                );

                // Convertir "favorito" manualmente (asumiendo que es un entero)
                boolean favorito = rs.getInt("favorito") == 1;

                // Agregar el restaurante a la lista
                restaurantes.add(new Restaurante(
                        rs.getString("pass"),
                        rs.getString("nombre"),
                        rs.getString("cif"),
                        favorito,
                        direccion
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de excepciones
        }

        return restaurantes; // Retorna la lista de restaurantes encontrados
    }


    public Restaurante selectPorId(String idRestaurante) {
        String sql = "SELECT * FROM Restaurante WHERE id = ?";
        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, idRestaurante);  // Asignar el ID a la consulta
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Crear el objeto Direccion
                Direccion direccion = new Direccion(
                        rs.getString("calle"),
                        rs.getInt("numero"),
                        rs.getString("complemento"),
                        rs.getInt("codigoPostal"),
                        rs.getString("municipio")
                );
                // Convertir "favorito" manualmente
                boolean favorito = rs.getInt("favorito") == 1; // Cambia aquí
                return new Restaurante(
                        rs.getString("pass"),
                        rs.getString("nombre"),
                        rs.getString("cif"),
                        favorito,
                        direccion
                );
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de excepciones
        }
        return null; // Retorna null si no se encontró el restaurante
    }

    public List<Restaurante> selectPorNombre(String cadenaBusqueda) {
        List<Restaurante> restaurantes = new ArrayList<>();
        String sql = "SELECT * FROM Restaurante WHERE nombre LIKE ?"; // Consulta SQL

        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + cadenaBusqueda + "%"); // Usar LIKE para coincidencias parciales
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // Crear el objeto Direccion
                Direccion direccion = new Direccion(
                        rs.getString("calle"),
                        rs.getInt("numero"),
                        rs.getString("complemento"),
                        rs.getInt("codigoPostal"),
                        rs.getString("municipio")
                );

                // Convertir "favorito" manualmente
                boolean favorito = rs.getInt("favorito") == 1; // Cambia aquí
                // Agregar el restaurante a la lista
                restaurantes.add(new Restaurante(
                        rs.getString("pass"),
                        rs.getString("nombre"),
                        rs.getString("cif"),
                        favorito,
                        direccion
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de excepciones
        }
        return restaurantes; // Retorna la lista de restaurantes encontrados
    }
}*/

