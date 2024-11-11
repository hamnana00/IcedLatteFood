package icedLatteFood.dominio.entidades;

import java.util.Collection;
import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
public class Cliente extends Usuario{
    @Column
    private String nombre;
    @Column
    private String apellidos;
    @Column
    private String dni;
    @Column
    private Collection<Restaurante> favoritos;
    @Column
    private Collection<icedLatteFood.dominio.entidades.Pedido> pedidos;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cliente_id")
    private List<Direccion> direcciones;

    // Constructor que inicializa el nombre, apellidos y dni
    public Cliente(String pass, String nombre, String apellidos, String dni){
        super(pass);
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
    }

    // Otros getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Collection<Restaurante> getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(Collection<Restaurante> favoritos) {
        this.favoritos = favoritos;
    }

    public Collection<icedLatteFood.dominio.entidades.Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(Collection<icedLatteFood.dominio.entidades.Pedido> pedidos) {
        this.pedidos = pedidos;
    }

     public List<Direccion> getDirecciones() {
        return direcciones;
    }
    public void setDirecciones(List<Direccion> direcciones) {
        this.direcciones = direcciones;
    }

    // Métodos adicionales
    public void anadirFavorito(Restaurante restaurante) {
        this.favoritos.add(restaurante);
    } // Añadir restaurante favorito a colección

    public void eliminarFavorito(Restaurante restaurante) {
        this.favoritos.remove(restaurante);
    }

    public void realizarPedido(icedLatteFood.dominio.entidades.Pedido pedido) {
        this.pedidos.add(pedido);
    }

    public void anadirDireccion(icedLatteFood.dominio.entidades.Direccion direccion) {
        this.direcciones.add(direccion);
    } // Añadir dirección

    public Collection<icedLatteFood.dominio.entidades.Pedido> verPedidos() {
        return this.pedidos; // Ver todos los pedidos
    }


    public Direccion getDireccion() {
        if (direcciones != null && !direcciones.isEmpty()) {
            // Retorna la primera dirección de la colección
            return direcciones.iterator().next();
        } else {
            // Si no hay direcciones, retornamos null o lanzamos una excepción, según lo que prefieras
            return null;
        }
    }
}
