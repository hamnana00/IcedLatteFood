package dominio.entidades;

import java.util.*;

public class Repartidor {

    Collection<ServicioEntrega> servicios;
    Collection<CodigoPostal> zonas;
    private String nombre;
    private String apellidos;
    private String nif;
    private int eficiencia;

    public Repartidor(String nombre, String apellidos, String nif, int eficiencia){
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nif = nif;
        this.eficiencia = eficiencia;
    }

}