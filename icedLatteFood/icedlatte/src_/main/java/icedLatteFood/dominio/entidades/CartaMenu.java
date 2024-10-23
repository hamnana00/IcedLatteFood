package dominio.entidades;

import java.util.ArrayList;
import java.util.List;

//Clase Menu
public class CartaMenu{
    private Collection<ItemMenu> items;
    private String nombre;
    private Restaurante restaurante;

    //Constructor
    public CartaMenu() {
        this.items  = new ArrayList<>();
    }

    //Método para agregar un plato al menú
    public void agregarPlato(ItemMenu items) {
        platos.add(items);
        System.out.println("Plato agregado: "+ items);
    }

    //Método para eliminar un plato del menú
    public void eliminarPlato(String nombrePlato) {
        for(ItemMenu items : platos) {
            if(items.getNombre().equalsIgnoreCase(nombrePlato)) {
                platos.remove(items);
                System.out.println("Plato eliminado: "+ nombrePlato);
                return;
            }
        }
        System.out.println("Plato no encontrado: "+ nombrePlato);
    }

    //Método para mostrar el menú
    public void mostrarMenu() {
        if (platos.isEmpty()) {
            System.out.println("El menú está vacío.");
        }else {
            System.out.println("Menú del restaurante:");
            for (ItemMenu items: platos) {
                System.out.println(items);
            }
        }
    }
}