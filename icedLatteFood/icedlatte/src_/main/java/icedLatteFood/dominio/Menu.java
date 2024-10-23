import java.util.ArrayList;
import java.util.List;

// Clase Menu
class Menu {
    private List<ItemMenu> items;

    // Constructor
    public Menu() {
        this.items = new ArrayList<>();
    }

    // Método para agregar un item al menú
    public void agregarItemMenu(ItemMenu itemMenu) {
        items.add(itemMenu);
        System.out.println("Item agregado: " + itemMenu);
    }

    // Método para eliminar un item del menú
    public void eliminarItemMenu(String nombreItem) {
        for (ItemMenu itemMenu : items) {
            if (itemMenu.getNombre().equalsIgnoreCase(nombreItem)) {
                items.remove(itemMenu);
                System.out.println("Item eliminado: " + nombreItem);
                return;
            }
        }
        System.out.println("Item no encontrado: " + nombreItem);
    }

    // Método para mostrar el menú
    public void mostrarMenu() {
        if (items.isEmpty()) {
            System.out.println("El menú está vacío.");
        } else {
            System.out.println("Menú del restaurante:");
            for (ItemMenu itemMenu : items) {
                System.out.println(itemMenu);
            }
        }
    }
}