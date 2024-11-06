package icedLatteFood.dominio.controladores;

import persistencia.*;
import dominio.entidades.*;
import java.util.*;


public class GestorClientes {

    private RestauranteDAO restauranteDAO;

    // Constructor que recibe el DAO de restaurantes
    public GestorClientes(RestauranteDAO restauranteDAO) {
        this.restauranteDAO = restauranteDAO;
    }

    // Buscar restaurante por zona (código postal)
    public List<Restaurante> buscarRestaurante(String codigoPostal) {
        return restauranteDAO.selectPorCodigoPostal(codigoPostal);
    }

    // Buscar restaurante por una cadena de búsqueda (en el nombre o en el menú)
    public List<Restaurante> buscarRestaurante(String codigoPostal, String cadenaBusqueda) {
        return restauranteDAO.selectPorCodigoPostalYTextoLibre(codigoPostal, cadenaBusqueda);
    }

    // Añadir un restaurante a la lista de favoritos de un cliente
    public void favorito(Cliente cliente, Restaurante restaurante) {
        if (cliente != null && restaurante != null) {
            cliente.anadirFavorito(restaurante);  // Añade el restaurante a los favoritos del cliente
        } else {
            throw new IllegalArgumentException("El cliente o el restaurante no pueden ser nulos");
        }
    }

    // Registrar un nuevo cliente
    public Cliente registrarCliente(String nombre, String apellido, Direccion direccion) {
        Cliente cliente = new Cliente(nombre, apellido, null); // Se crea el cliente con nombre y apellido
        cliente.anadirDireccion(direccion);  // Se añade la dirección al cliente
        return cliente;  // Devuelve el cliente registrado (en un caso real, probablemente guardarías al cliente en la base de datos)
    }

    // Alta de una nueva dirección para un cliente
    public Direccion altaDireccion(String calle, String numero, String complemento, String codigoPostal, String municipio) {
        try {
            int numeroParsed = Integer.parseInt(numero);
            int codigoPostalParsed = Integer.parseInt(codigoPostal);
            return new Direccion(calle, numeroParsed, complemento, codigoPostalParsed, municipio);
        } catch (NumberFormatException e) {
            System.out.println("Error en los datos numéricos de la dirección: " + e.getMessage());
            return null;  // Se podría lanzar una excepción o manejar de otra forma
        }
    }
}