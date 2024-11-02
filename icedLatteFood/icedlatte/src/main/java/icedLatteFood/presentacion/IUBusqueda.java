package icedLatteFood.presentacion;
import dominio.controladores.GestorClientes;
import icedLatteFood.dominio.entidades.*;
import java.util.*;

import icedLatteFood.dominio.entidades.Restaurante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class IUBusqueda {

    @Autowired
    private GestorClientes gestorClientes;  // Instancia del gestor de clientes

    public IUBusqueda(GestorClientes gestorClientes) {
        this.gestorClientes = gestorClientes;
    }

    /**
     * Búsqueda de restaurantes por código postal.
     * @param zona CódigoPostal del área donde buscar restaurantes.
     * @return Lista de restaurantes en la zona.
     */
    public List<Restaurante> buscar(icedLatteFood.dominio.entidades.CodigoPostal zona) {
        return gestorClientes.buscarRestaurante(zona);
    }

    /**
     * Búsqueda de restaurantes por código postal y texto de búsqueda.
     * @param zona CódigoPostal del área.
     * @param textoBusqueda Cadena de texto para buscar en nombre o menú.
     * @return Lista de restaurantes que coinciden con los criterios.
     */
    public List<Restaurante> buscar(icedLatteFood.dominio.entidades.CodigoPostal zona, String textoBusqueda) {
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

    // Muestra todos los restaurantes o los resultados de la búsqueda
    @GetMapping("/restaurantes")
    public String mostrarRestaurantes(@RequestParam(value = "zona", required = false) String zona,
                                      @RequestParam(value = "busqueda", required = false) String busqueda,
                                      Model model) {
        List<Restaurante> restaurantes;

        // Condición para cargar todos o hacer la búsqueda
        if ((zona != null && !zona.isEmpty()) || (busqueda != null && !busqueda.isEmpty())) {
            restaurantes = gestorRestaurante.buscarRestaurantes(zona, busqueda);
        } else {
            restaurantes = gestorRestaurante.obtenerTodosRestaurantes();
        }

        model.addAttribute("restaurantes", restaurantes);
        return "restaurantes"; // Llama a "restaurantes.html"
    }

}