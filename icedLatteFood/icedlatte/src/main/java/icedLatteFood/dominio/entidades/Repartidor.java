package icedLatteFood.dominio.entidades;

import java.util.*;
import jakarta.persistence.*;

@Entity
public class Repartidor extends Usuario{

    @Column
    private String nombre;
    @Column
    private String apellidos;
    @Column
    private String nif;
    @Column
    private int eficiencia;
    @OneToMany(mappedBy = "repartidor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ServicioEntrega> serviciosEntrega = new ArrayList<>();
    @ElementCollection(targetClass = CodigoPostal.class)
    @CollectionTable(name = "repartidor_zonas", joinColumns = @JoinColumn(name = "idUsuario_rep"))
    @Enumerated(EnumType.STRING)
    @Column(name = "codigo_postal")
    private List<CodigoPostal> zonas = new ArrayList<>();

    // Constructor
    public Repartidor(String pass, String nombre, String apellidos, String nif, int eficiencia) {
        super(pass); // Asigna un ID único al repartidor
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nif = nif;
        this.eficiencia = eficiencia;
    }

    // Método para obtener el ID del repartidor

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

    public List<ServicioEntrega> getServiciosEntrega() {
        return serviciosEntrega;
    }

    public void setServiciosEntrega(List<ServicioEntrega> serviciosEntrega) {
        this.serviciosEntrega = serviciosEntrega;
    }

    public List<CodigoPostal> getZonas() {
        return zonas;
    }

    public void setZonas(List<CodigoPostal> zonas) {
        this.zonas = zonas;
    }
}
