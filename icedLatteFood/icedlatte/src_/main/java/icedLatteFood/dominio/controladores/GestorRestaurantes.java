package dominio.controladores;

import java.util.List;

// Clase GestorRestaurantes
public class GestorRestaurantes {

    // Funcionalidad para registrar un nuevo restaurante
    public Restaurante registrarRestaurante(String nombre, String cif, Direccion d) {
        Restaurante nuevoRestaurante = new Restaurante(nombre, cif, d);
        System.out.println("Restaurante registrado: " + nombre);
        return nuevoRestaurante;
    }

    // Funcionalidad para editar la carta de un restaurante
    public void editarCarta(String nombreRestaurante, List<ItemMenu> items) {
        Restaurante restaurante = buscarRestaurantePorNombre(nombreRestaurante);
        if (restaurante != null) {
            Menu menu = restaurante.getMenu();
            for (ItemMenu item : items) {
                menu.agregarItemMenu(item);
            }
            System.out.println("Carta actualizada para el restaurante: " + nombreRestaurante);
        } else {
            System.out.println("Restaurante no encontrado: " + nombreRestaurante);
        }
    }

    // Funcionalidad para crear un nuevo item del menú
    public ItemMenu crearItem(String nombre, double precio, TipoItemMenu tipo) {
        ItemMenu nuevoItem = new ItemMenu(nombre, precio, tipo);
        System.out.println("Item creado: " + nuevoItem);
        return nuevoItem;
    }

    // Funcionalidad auxiliar para buscar un restaurante por nombre (opcional para la funcionalidad)
    private Restaurante buscarRestaurantePorNombre(String nombre) {
        // Aquí puedes implementar la lógica para buscar el restaurante en una lista o base de datos
        return null; // Esto es solo un placeholder, el método debe retornar un restaurante válido
    }
}