package icedLatteFood.dominio.entidades;

// Clase ItemMenu
public class ItemMenu {
    private String nombre;
    private double precio;

    // Constructor modificado
    public ItemMenu(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    // Getters y Setters
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
