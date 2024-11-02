package dominio.controladores;

import java.util.*;
import icedLatteFood.dominio.entidades.Direccion;

public class GestorClientes {

    private icedLatteFood.persistencia.RestauranteDAO restauranteDAO;
    private icedLatteFood.persistencia.ClienteDAO clienteDAO;

    // Constructor que recibe el DAO de restaurantes
    public GestorClientes(icedLatteFood.persistencia.RestauranteDAO restauranteDAO, icedLatteFood.persistencia.ClienteDAO clienteDAO) {
        this.restauranteDAO = restauranteDAO;
        this.clienteDAO = clienteDAO;
    }

    // Buscar restaurante por zona (código postal)
    public List<icedLatteFood.dominio.entidades.Restaurante> buscarRestaurante(icedLatteFood.dominio.entidades.CodigoPostal codigoPostal) {
        return restauranteDAO.selectPorCodigoPostal(String.valueOf(codigoPostal.getCodigo()));
    }

    //Buscar restaurante solo por cadena de búsqueda
    public List<icedLatteFood.dominio.entidades.Restaurante> buscarRestauranteCadena(String cadenaBusqueda) {
        return restauranteDAO.selectPorNombre(cadenaBusqueda);
    }
    // Añadir un restaurante a la lista de favoritos de un cliente
    public void favorito(icedLatteFood.dominio.entidades.Cliente cliente, icedLatteFood.dominio.entidades.Restaurante restaurante) {
        if (cliente != null && restaurante != null) {
            cliente.anadirFavorito(restaurante);  // Añade el restaurante a los favoritos del cliente
        } else {
            throw new IllegalArgumentException("El cliente o el restaurante no pueden ser nulos");
        }
    }

    // Registrar un nuevo cliente
    public icedLatteFood.dominio.entidades.Cliente registrarCliente(String nombre, String apellido, Direccion direccion) {
        icedLatteFood.dominio.entidades.Cliente cliente = new icedLatteFood.dominio.entidades.Cliente(nombre, apellido, null); // Se crea el cliente con nombre y apellido
        cliente.anadirDireccion(direccion);  // Se añade la dirección al cliente
        return cliente;  // Devuelve el cliente registrado (en un caso real, probablemente guardarías al cliente en la base de datos)
    }

    // Alta de una nueva dirección para un cliente
    public icedLatteFood.dominio.entidades.Direccion altaDireccion(String calle, String numero, String complemento, String codigoPostal, String municipio) {
        try {
            int numeroParsed = Integer.parseInt(numero);
            int codigoPostalParsed = Integer.parseInt(codigoPostal);
            return new icedLatteFood.dominio.entidades.Direccion(calle, numeroParsed, complemento, codigoPostalParsed, municipio);
        } catch (NumberFormatException e) {
            System.out.println("Error en los datos numéricos de la dirección: " + e.getMessage());
            return null;  // Se podría lanzar una excepción o manejar de otra forma
        }
    }
}