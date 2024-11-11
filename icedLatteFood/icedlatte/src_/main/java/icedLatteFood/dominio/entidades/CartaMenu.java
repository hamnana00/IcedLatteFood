package dominio.entidades;

import java.util.ArrayList;
import java.util.List;

//Clase Menu
public class CartaMenu{
    private List<ItemMenu> itemMenu;

    //Constructor
    public CartaMenu() {
        this.itemMenu = new ArrayList<>();
    }

    //Método para agregar un plato al menú
    public void agregarPlato(ItemMenu itemMenu) {
        platos.add(itemMenu);
        System.out.println("Plato agregado: "+ itemMenu);
    }

    //Método para eliminar un plato del menú
    public void eliminarItemMenu(String nombreItemMenu) {
        for(ItemMenu itemMenu : platos) {
            if(ItemMenu.getNombre().equalsIgnoreCase(nombreItemMenu)) {
                platos.remove(itemMenu);
                System.out.println("Plato eliminado: "+ itemMenu);
                return;
            }
        }
        System.out.println("Plato no encontrado: "+ nombreItemMenu);
    }

    //Método para mostrar el menú
    public void mostrarMenu() {
        if (itemMenu.isEmpty()) {
            System.out.println("El menú está vacío.");
        }else {
            System.out.println("Menú del restaurante:");
            for (ItemMenu itemMenu: itemMenu) {
                System.out.println(itemMenu);
            }
        }
    }
}