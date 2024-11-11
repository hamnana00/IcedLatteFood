package icedLatteFood.dominio.entidades;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

@Entity
public class CartaMenu {
//poner abstract entity para q las entidades hereden de este
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCarta;
    @ManyToOne
    @JoinColumn(name = "restaurante_id")
    private Restaurante restaurante;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemMenu> itemMenu;

    // Constructor
    public CartaMenu() {
        this.itemMenu = new ArrayList<>();
    }

    public Long getIdCarta() { return idCarta; }
    public void setIdCarta(Long idCarta) { this.idCarta = idCarta; }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    // Método para agregar un plato al menú
    public void agregarItemMenu(icedLatteFood.dominio.entidades.ItemMenu itemMenu) {
        this.itemMenu.add(itemMenu); // Cambié "platos" por "itemMenu"
        System.out.println("Plato agregado: " + itemMenu);
    }

    // Método para eliminar un plato del menú
    public void eliminarItemMenu(String nombreItemMenu) {
        for (icedLatteFood.dominio.entidades.ItemMenu itemMenu : new ArrayList<>(this.itemMenu)) { // Usamos una copia para evitar ConcurrentModificationException
            if (itemMenu.getNombre().equalsIgnoreCase(nombreItemMenu)) {
                this.itemMenu.remove(itemMenu);
                System.out.println("Plato eliminado: " + itemMenu);
                return;
            }
        }
        System.out.println("Plato no encontrado: " + nombreItemMenu);
    }

    // Método para mostrar el menú
    public void mostrarMenu() {
        if (itemMenu.isEmpty()) {
            System.out.println("El menú está vacío.");
        } else {
            System.out.println("Menú del restaurante:");
            for (icedLatteFood.dominio.entidades.ItemMenu itemMenu : this.itemMenu) {
                System.out.println(itemMenu);
            }
        }
    }

    public void setItems(List<ItemMenu> items) {
    }

    public void addItem(ItemMenu nuevoItem) {
    }
}
