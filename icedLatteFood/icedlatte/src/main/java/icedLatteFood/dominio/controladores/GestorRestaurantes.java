package icedLatteFood.dominio.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import icedLatteFood.dominio.entidades.Restaurante;
import icedLatteFood.dominio.entidades.ItemMenu;
import icedLatteFood.dominio.entidades.Direccion;
import icedLatteFood.dominio.entidades.TipoItemMenu;
import icedLatteFood.persistencia.RestauranteDAO;
import icedLatteFood.servicios.DataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GestorRestaurantes {

    @Autowired
    private DataBaseService dataBaseService;
    private RestauranteDAO restauranteDAO;

    public GestorRestaurantes(RestauranteDAO restauranteDAO) {
        this.restauranteDAO = restauranteDAO;
    }

    // Método para obtener la instancia de RestauranteDAO
    public RestauranteDAO getRestauranteDAO() {
        return restauranteDAO;
    }

    // Mostrar restaurantes
    public List<Restaurante> obtenerTodosRestaurantes() {
        return restauranteDAO.selectAll();  // IMPLEMENTAR EN EL DAO! no olvidar
    }

    // Buscar restaurante por código postal o texto
    /*public List<Restaurante> buscarRestaurantes(String codigoPostal, String texto) {
        if (codigoPostal != null && !codigoPostal.isEmpty() && texto != null && !texto.isEmpty()) {
            return restauranteDAO.selectPorCodigoPostalYTextoLibre(codigoPostal, texto);
        } else if (codigoPostal != null && !codigoPostal.isEmpty()) {
            return restauranteDAO.selectPorCodigoPostal(codigoPostal);
        } else {
            return restauranteDAO.selectAll();
        }
    }*/

    // Registrar un nuevo restaurante
    public Restaurante registrarRestaurante(String pass, String nombre, String cif, boolean favorito, Direccion direccion) {
        // Crear una nueva instancia de Restaurante con los datos proporcionados
        Restaurante nuevoRestaurante = new Restaurante(pass, nombre, cif, favorito, direccion);
        // Aquí podrías llamar al DAO para guardar el nuevo restaurante en la base de datos
        // restauranteDAO.insert(nuevoRestaurante);
        return nuevoRestaurante;
    }

    // Funcionalidad para editar la carta del restaurante
    public void editarCarta(Restaurante restaurante, List<ItemMenu> items) {
        // Se reemplaza el menú actual del restaurante con los nuevos items
        restaurante.getCartaMenu().setItems(items);
        // También podrías actualizar el menú en la base de datos usando el DAO
        // menuDAO.update(restaurante.getMenu());
    }

    // Crear un nuevo ItemMenu y añadirlo al restaurante
    public void crearItem(Restaurante restaurante, String nombre, double precio) {
        // Validar que el restaurante no sea nulo
        if (restaurante == null) {
            throw new IllegalArgumentException("El restaurante no puede ser nulo");
        }

        // Crear una nueva instancia de ItemMenu
        ItemMenu nuevoItem = new ItemMenu(nombre, precio);

        // Añadir el nuevo ítem al menú del restaurante
        restaurante.getCartaMenu().addItem(nuevoItem);  // Asumiendo que getMenu() devuelve un objeto que tiene un método addItem

        // Si deseas, aquí puedes agregar lógica para guardar el nuevo ítem en la base de datos, si es necesario.
        // itemMenuDAO.insert(nuevoItem);
    }
    @GetMapping("/test-connection-restaurantes")
    public String testConnection() {
        dataBaseService.testConnection();
        return "Connection tested!";
    }
}
