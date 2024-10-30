package dominio.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import dominio.entidades.Cliente;
import dominio.entidades.ItemMenu;
import dominio.entidades.Restaurante;

import java.util.List;

@Controller
public class PedidoController {

    private final GestorPedidos gestorPedidos;

    public PedidoController(GestorBaseDatos gestorBD) {
        this.gestorPedidos = new GestorPedidos(gestorBD);
    }

    @GetMapping("/pedido")
    public String mostrarFormularioPedido(Model model) {
        model.addAttribute("restaurantes", obtenerRestaurantes());
        model.addAttribute("itemsMenu", obtenerItemsMenu());
        return "pedido"; // Nombre del archivo HTML en templates
    }

    @PostMapping("/realizarPedido")
    public String realizarPedido(@RequestParam("clienteId") int clienteId,
                                 @RequestParam("restauranteId") int restauranteId,
                                 @RequestParam("itemIds") List<Integer> itemIds) {
        Cliente cliente = obtenerClientePorId(clienteId); // Método para obtener cliente
        Restaurante restaurante = obtenerRestaurantePorId(restauranteId); // Método para obtener restaurante
        List<ItemMenu> items = obtenerItemsPorIds(itemIds); // Método para obtener items

        gestorPedidos.realizarPedido(cliente, restaurante, items);
        return "redirect:/pedido"; // Redirigir a la página de pedidos
    }

    // Métodos adicionales para obtener datos (clientes, restaurantes, items)...

    private List<Restaurante> obtenerRestaurantes() {
        // Lógica para obtener la lista de restaurantes
    }

    private List<ItemMenu> obtenerItemsMenu() {
        // Lógica para obtener la lista de items del menú
    }

    private Cliente obtenerClientePorId(int clienteId) {
        // Lógica para obtener un cliente por su ID
    }

    private Restaurante obtenerRestaurantePorId(int restauranteId) {
        // Lógica para obtener un restaurante por su ID
    }

    private List<ItemMenu> obtenerItemsPorIds(List<Integer> itemIds) {
        // Lógica para obtener una lista de items por sus IDs
    }
}
