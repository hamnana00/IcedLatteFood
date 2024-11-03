package icedLatteFood.dominio.entidades;

public enum EstadoPedido {
	PEDIDO("El pedido ha sido realizado"),
	PAGADO("El pedido ha sido pagado"),
	RECOGIDO("El pedido ha sido recogido por el cliente"),
	ENTREGADO("El pedido ha sido entregado al cliente");

	private final String descripcion;

	EstadoPedido(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}
}
