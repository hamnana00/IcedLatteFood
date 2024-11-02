package icedLatteFood.dominio.entidades;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;

// Clase Restaurante
public class Restaurante {
    private String nombre;
    private String cif;
    private boolean favorito;
    private Collection<CartaMenu> menu;
    private Collection<icedLatteFood.dominio.entidades.Pedido> pedidos;

    // Constructor
    public Restaurante(String nombre, String cif, boolean favorito) {
        this.nombre = nombre;
        this.cif = cif;
        this.favorito = favorito;
        this.menu = (Collection<CartaMenu>) new Menu();
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public String getCif() {
        return cif;
    }

    public CartaMenu getMenu() {
        return (CartaMenu) menu;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public boolean isFavorito() { return favorito; }

    public void setFavorito(boolean favorito) { this.favorito = favorito; }

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