package dominio.entidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;

// Clase Restaurante
public class Restaurante {
    private String nombre;
    private String cif;
    private Collection<CartaMenu> menu;
    private Collection<Pedido> pedidos;

    // Constructor
    public Restaurante(String nombre, String cif) {
        this.nombre = nombre;
        this.cif = cif;
        this.menu = new Menu();
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public String getCif() {
        return cif;
    }

    public CartaMenu getMenu() {
        return menu;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    // Métodos para interactuar con el menú
    public void agregarItemMenu(ItemMenu itemMenu) {
        menu.agregarItemMenu(itemMenu);
    }

    public void eliminarItemMenu(String nombreItem) {
        menu.eliminarItemMenu(nombreItem);
    }

    public void mostrarMenu() {
        menu.mostrarMenu();
    }
}
