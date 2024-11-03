package icedLatteFood.dominio.entidades;

import java.util.Collection;

public class Cliente {
    private int id; // Atributo para almacenar el ID del cliente
    private String nombre;
    private String apellidos;
    private String dni;
    private Collection<Restaurante> favoritos;
    private Collection<icedLatteFood.dominio.entidades.Pedido> pedidos;
    private Collection<icedLatteFood.dominio.entidades.Direccion> direcciones;

    // Constructor que inicializa el nombre, apellidos y dni
    public Cliente(int idCli, String nombre, String apellidos, String dni) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
    }

    // Getter para el ID del cliente
    public int getId() {
        return id; // Retorna el ID del cliente
    }

    // Setter para el ID del cliente
    public void setId(int id) {
        if (id > 0) { // Validación del ID
            this.id = id; // Asigna el ID del cliente
        } else {
            throw new IllegalArgumentException("El ID del cliente debe ser positivo"); // Lanza excepción si el ID no es válido
        }
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

    public Collection<icedLatteFood.dominio.entidades.Direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(Collection<icedLatteFood.dominio.entidades.Direccion> direcciones) {
        this.direcciones = direcciones;
    }

    // Métodos adicionales
    public void anadirFavorito(Restaurante restaurante) {
        this.favoritos.add(restaurante);
    } // Añadir restaurante favorito a colección

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
