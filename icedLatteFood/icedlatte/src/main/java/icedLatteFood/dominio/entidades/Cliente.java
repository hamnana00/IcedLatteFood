package dominio.entidades;

import java.util.Collection;
//
public class Cliente {
	private String nombre;
	private String apellidos;
	private String dni;
	Collection<Restaurante> favoritos;
	Collection<Pedido> pedidos;
	Collection<Direccion> direcciones;

    public Cliente(String nombre, String apellidos, String dni) {
        this.nombre = nombre;
        this.apellidos=apellidos;
        this.dni=dni;
    }
	
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
    public Collection<Pedido> getPedidos() {
        return pedidos;
    }
    public void setPedidos(Collection<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
    public Collection<Direccion> getDirecciones() {
        return direcciones;
    }
    public void setDirecciones(Collection<Direccion> direcciones) {
        this.direcciones = direcciones;
    }
    
    //otros metodos
    public void anadirFavorito(Restaurante restaurante) {
        this.favoritos.add(restaurante);
    } //anadir restaurante favorito a coleccion

    public void realizarPedido(Pedido pedido) {
        this.pedidos.add(pedido);
    }

    public void anadirDireccion(Direccion direccion) {
        this.direcciones.add(direccion);
    }//anadir direccion

    public Collection<Pedido> verPedidos() {
        return this.pedidos;
    }//ver todos los pedidos
}
