import java.util.ArrayList;
import java.util.List;

//Clase Plato
public class Plato{
    private String nombre;
    private double precio;

    //Constructor
    public Plato(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    //Getters y Setters
    public String getNombre(){
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
        return "Plato: "+ nombre + "Precio: €"+ precio;
    }
}