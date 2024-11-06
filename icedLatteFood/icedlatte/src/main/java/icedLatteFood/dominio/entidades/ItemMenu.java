package icedLatteFood.dominio.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// Clase ItemMenu
@Entity
public class ItemMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idItem;
    @Column
    private String nombre;
    @Column
    private double precio;

    // Constructor modificado
    public ItemMenu(String nombre, double precio) {
        this.idItem = idItem;
        this.nombre = nombre;
        this.precio = precio;
    }

    // Getters y Setters

    public long getIdItem() {
        return idItem;
    }
    public void setIdItem(long idItem) {this.idItem = idItem;}

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Plato: " + nombre + " Precio: €" + precio;
    }
}
