package icedLatteFood.dominio.entidades;

import java.util.Collection;
//
public class Cliente {
	private String nombre;
	private String apellidos;
	private String dni;
	Collection<Restaurante> favoritos;
	Collection<dominio.entidades.Pedido> pedidos;
	Collection<dominio.entidades.Direccion> direcciones;

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
    public Collection<dominio.entidades.Pedido> getPedidos() {
        return pedidos;
    }
    public void setPedidos(Collection<dominio.entidades.Pedido> pedidos) {
        this.pedidos = pedidos;
    }
    public Collection<dominio.entidades.Direccion> getDirecciones() {
        return direcciones;
    }
    public void setDirecciones(Collection<dominio.entidades.Direccion> direcciones) {
        this.direcciones = direcciones;
    }
    
    //otros metodos
    public void anadirFavorito(Restaurante restaurante) {
        this.favoritos.add(restaurante);
    } //anadir restaurante favorito a coleccion

    public void realizarPedido(dominio.entidades.Pedido pedido) {
        this.pedidos.add(pedido);
    }

    public void anadirDireccion(dominio.entidades.Direccion direccion) {
        this.direcciones.add(direccion);
    }//anadir direccion

    public Collection<dominio.entidades.Pedido> verPedidos() {
        return this.pedidos;
    }//ver todos los pedidos
}
