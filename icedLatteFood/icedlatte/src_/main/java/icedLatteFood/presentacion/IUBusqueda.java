package presentacion;
import dominio.controladores.GestorClientes;
import dominio.entidades.*;
import java.util.*;

public class IUBusqueda {

    private GestorClientes gestorClientes;  // Instancia del gestor de clientes

    // Constructor que recibe el GestorClientes
    public IUBusqueda(GestorClientes gestorClientes) {
        this.gestorClientes = gestorClientes;
    }

    /**
     * Búsqueda de restaurantes por código postal.
     * @param zona CódigoPostal del área donde buscar restaurantes.
     * @return Lista de restaurantes en la zona.
     */
    public List<Restaurante> buscar(CodigoPostal zona) {
        return gestorClientes.buscarRestaurante(zona);
    }

    /**
     * Búsqueda de restaurantes por código postal y texto de búsqueda.
     * @param zona CódigoPostal del área.
     * @param textoBusqueda Cadena de texto para buscar en nombre o menú.
     * @return Lista de restaurantes que coinciden con los criterios.
     */
    public List<Restaurante> buscar(CodigoPostal zona, String textoBusqueda) {
        return gestorClientes.buscarRestaurante(zona, textoBusqueda);
    }

    /**
     * Marca un restaurante como favorito para el cliente actual.
     * @param cliente Cliente que desea marcar el restaurante.
     * @param idRestaurante ID del restaurante a marcar como favorito.
     */
    public void marcarFavorito(String idRestaurante) {
        // Primero buscamos el restaurante en el sistema (puedes agregar el método en GestorClientes)
        Restaurante restaurante = gestorClientes.obtenerRestaurantePorId(idRestaurante);
        if (restaurante != null) {
            gestorClientes.favorito(cliente, restaurante);
        } else {
            System.out.println("Restaurante no encontrado con ID: " + idRestaurante);
        }
    }

}