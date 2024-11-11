package icedLatteFood.presentacion;

import icedLatteFood.dominio.controladores.GestorClientes;
import java.util.*;
import icedLatteFood.dominio.entidades.CodigoPostal;
import icedLatteFood.dominio.entidades.Restaurante;
import icedLatteFood.dominio.entidades.Cliente; // Importar la clase Cliente
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class IUBusqueda {/*

    @Autowired
    private GestorClientes gestorClientes;  // Instancia del gestor de clientes

    public IUBusqueda(GestorClientes gestorClientes) {
        this.gestorClientes = gestorClientes;
    }

    // Método auxiliar para encontrar el CodigoPostal basado en un número
    private CodigoPostal obtenerCodigoPostalPorNumero(int numero) {
        for (CodigoPostal cp : CodigoPostal.values()) {
            if (cp.getCodigo() == numero) {
                return cp;
            }
        }
        return null; // Devuelve null si no se encuentra
    }


     * Búsqueda de restaurantes por código postal y texto de búsqueda.
     * @param zona CódigoPostal del área.
     * @param textoBusqueda Cadena de texto para buscar en nombre o menú.
     * @return Lista de restaurantes que coinciden con los criterios.

    public List<Restaurante> buscar(CodigoPostal zona, String textoBusqueda) {
        return gestorClientes.buscarRestaurante(zona, textoBusqueda);
    }

    /**
     * Marca un restaurante como favorito para el cliente actual.
     * @param cliente Cliente que desea marcar el restaurante.
     * @param idRestaurante ID del restaurante a marcar como favorito.

    public void marcarFavorito(Cliente cliente, String idRestaurante) {
        // Primero buscamos el restaurante en el sistema (puedes agregar el método en GestorClientes)
        Restaurante restaurante = gestorClientes.obtenerRestaurantePorId(idRestaurante);
        if (restaurante != null && cliente != null) {
            gestorClientes.favorito(cliente, restaurante);
        } else {
            System.out.println("Cliente o Restaurante no encontrado.");
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
            // Convertir la zona a un número y buscar el CodigoPostal correspondiente
            CodigoPostal codigoPostal = zona != null ? obtenerCodigoPostalPorNumero(Integer.parseInt(zona)) : null;
            restaurantes = gestorClientes.buscarRestaurante(codigoPostal, busqueda);
        } else {
            // Si no tienes gestorRestaurante, usa gestorClientes u otro método para obtener todos los restaurantes
            restaurantes = gestorClientes.buscarRestauranteCadena(""); // Método para obtener todos
        }

        model.addAttribute("restaurantes", restaurantes);
        return "restaurantes"; // Llama a "restaurantes.html"
    }*/
}
