package icedLatteFood.dominio.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.List;
import java.util.ArrayList;

// Clase Restaurante
@Entity
public class Restaurante extends Usuario{
    @Column
    private String nombre;
    @Column
    private String cif;
    @Column
    private boolean favorito;  // Atributo para saber si el restaurante es favorito
    @OneToOne
    private Direccion direccion; // Atributo para la dirección del restaurante
    @OneToMany(mappedBy = "restaurante")
    private List<CartaMenu> cartaMenu;
    @OneToMany(mappedBy = "pedidos")
    private List<Pedido> pedidos;

    // Constructor
    public Restaurante(String pass, String nombre, String cif, boolean favorito, Direccion direccion) {
        super(pass);
        this.nombre = nombre;
        this.cif = cif;
        this.favorito = favorito; // Ahora inicializamos favorito
        this.direccion = direccion;
        this.cartaMenu = new ArrayList<>();
        this.pedidos = new ArrayList<>();
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    //DESCOMENTAR PARA QUE FUNCION HACER PEDIDO PUEDA TRABAJAR
    /*public CartaMenu getCartaMenu() {
        return cartaMenu;
    }

    // Métodos para interactuar con la carta de menú
    public void agregarItemMenu(ItemMenu itemMenu) {
        cartaMenu.agregarItemMenu(itemMenu);
    }

    public void eliminarItemMenu(String nombreItem) {
        cartaMenu.eliminarItemMenu(nombreItem);
    }

    public void mostrarMenu() {
        cartaMenu.mostrarMenu();
    }

    public void modificarMenu(CartaMenu cartaMenu) {
        this.cartaMenu = cartaMenu;
    }*/
    /*public void mostrarPedidos() {
        for (Pedido pedido : pedidos) {
            System.out.println(pedido);
        }
    }*/
}
