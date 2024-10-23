import java.util.ArrayList;
import java.util.List;

//Clase Menu
public class Menu{
    private List<Plato> platos;

    //Constructor
    public Menu() {
        this.platos = new ArrayList<>();
    }

    //Método para agregar un plato al menú
    public void agregarPlato(Plato plato) {
        platos.add(plato);
        System.out.println("Plato agregado: "+ plato);
    }

    //Método para eliminar un plato del menú
    public void eliminarPlato(String nombrePlato) {
        for(Plato plato : platos) {
            if(plato.getNombre().equalsIgnoreCase(nombrePlato)) {
                platos.remove(plato);
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
            for (Plato plato: platos) {
                System.out.println(plato);
            }
        }
    }
}