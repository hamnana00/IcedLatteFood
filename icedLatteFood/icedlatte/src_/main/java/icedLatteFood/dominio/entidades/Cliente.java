public class Cliente extends Usuario{
	private String nombre;
	private String apellidos;
	private String dni;
	private Collection<Restaurante> favoritos;
	private Collection<Pedido> pedidos;
	private Direccion direcciones;
	
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
}
