package icedLatteFood.dominio.entidades;

import java.util.*;

public class Repartidor {
    private static int idCounter = 0; // Contador estático para generar IDs únicos
    private int id; // ID del repartidor
    private String nombre;
    private String apellidos;
    private String nif;
    private int eficiencia;

    // Colecciones para servicios y zonas
    Collection<ServicioEntrega> servicios;
    Collection<CodigoPostal> zonas;

    // Constructor
    public Repartidor(String nombre, String apellidos, String nif, int eficiencia) {
        this.id = ++idCounter; // Asigna un ID único al repartidor
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nif = nif;
        this.eficiencia = eficiencia;
        this.servicios = new ArrayList<>(); // Inicializa la colección de servicios
        this.zonas = new ArrayList<>(); // Inicializa la colección de zonas
    }

    // Método para obtener el ID del repartidor
    public int getId() {
        return id; // Retorna el ID del repartidor
    }

    // Métodos adicionales para obtener información del repartidor
    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getNif() {
        return nif;
    }

    public int getEficiencia() {
        return eficiencia;
    }

    public Collection<ServicioEntrega> getServicios() {
        return servicios; // Devuelve la colección de servicios
    }

    public void addServicio(ServicioEntrega servicio) {
        if (servicio != null) {
            servicios.add(servicio); // Añade un servicio a la colección
        }
    }

    public Collection<CodigoPostal> getZonas() {
        return zonas; // Devuelve la colección de zonas
    }

    public void addZona(CodigoPostal zona) {
        if (zona != null) {
            zonas.add(zona); // Añade una zona a la colección
        }
    }

    public void setZonas(List<CodigoPostal> zonas) {
        if (zonas != null) {
            this.zonas.clear(); // Limpiamos las zonas actuales
            this.zonas.addAll(zonas); // Añadimos las nuevas zonas
        } else {
            throw new IllegalArgumentException("La lista de zonas no puede ser nula");
        }
    }
}
