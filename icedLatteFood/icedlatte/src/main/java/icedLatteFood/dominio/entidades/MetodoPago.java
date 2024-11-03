package icedLatteFood.dominio.entidades;

public enum MetodoPago {
	PAYPAL("PayPal"),
	CREDIT_CARD("Tarjeta de Crédito"),
	BANK_TRANSFER("Transferencia Bancaria"),
	CASH_ON_DELIVERY("Pago Contra Entrega");

	private final String descripcion;

	MetodoPago(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}
}
