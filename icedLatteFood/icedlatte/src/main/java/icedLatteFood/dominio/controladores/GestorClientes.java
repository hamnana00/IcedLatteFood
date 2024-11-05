package dominio.controladores;

import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import icedLatteFood.dominio.entidades.CodigoPostal;
import icedLatteFood.dominio.entidades.Direccion;
import icedLatteFood.dominio.entidades.Restaurante;
import icedLatteFood.dominio.entidades.Cliente;


public class GestorClientes {

    private icedLatteFood.persistencia.RestauranteDAO restauranteDAO;
    private icedLatteFood.persistencia.ClienteDAO clienteDAO;

    // Constructor que recibe el DAO de restaurantes
    public GestorClientes(icedLatteFood.persistencia.RestauranteDAO restauranteDAO, icedLatteFood.persistencia.ClienteDAO clienteDAO) {
        this.restauranteDAO = restauranteDAO;
        this.clienteDAO = clienteDAO;
    }

    // Buscar restaurante por zona (código postal)
    public List<icedLatteFood.dominio.entidades.Restaurante> buscarRestaurante(CodigoPostal codigoPostal, String textoBusqueda) {
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
    public icedLatteFood.dominio.entidades.Cliente registrarCliente(String pass, String nombre, String apellido, Direccion direccion) {
        // Crear un nuevo cliente temporal sin ID
        icedLatteFood.dominio.entidades.Cliente cliente = new icedLatteFood.dominio.entidades.Cliente(pass, nombre, apellido, null); // ID temporal
        cliente.anadirDireccion(direccion);  // Se añade la dirección al cliente

        // Guardar el cliente en la base de datos y obtener el ID generado
        int idGenerado = clienteDAO.insert(cliente); // Suponiendo que insert devuelve el ID del nuevo cliente como int
        if (idGenerado > 0) {
            cliente.setIdUsuario(String.valueOf(idGenerado));// Asignar el ID generado al cliente
        } else {
            System.out.println("Error al registrar el cliente.");
        }

        return cliente;  // Devuelve el cliente registrado
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

    // Clase GestorClientes
    public Restaurante obtenerRestaurantePorId(String idRestaurante) {
        // Comprobar que el ID no sea nulo o vacío
        if (idRestaurante == null || idRestaurante.isEmpty()) {
            throw new IllegalArgumentException("El ID del restaurante no puede ser nulo o vacío");
        }

        // Usar el DAO para obtener el restaurante
        Restaurante restaurante = restauranteDAO.selectPorId(idRestaurante);

        // Verificar si se encontró el restaurante
        if (restaurante == null) {
            System.out.println("No se encontró el restaurante con ID: " + idRestaurante);
            // Aquí podrías lanzar una excepción o manejar el caso de otra forma
        }

        return restaurante;  // Retorna el restaurante encontrado (o null si no se encontró)
    }

