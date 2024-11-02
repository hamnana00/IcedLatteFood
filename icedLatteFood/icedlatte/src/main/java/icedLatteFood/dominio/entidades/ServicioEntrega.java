package icedLatteFood.dominio.entidades;

import java.time.LocalDateTime;

public class ServicioEntrega {
    Pedido pedido;
    Direccion direccion;
    Repartidor repartidor;
    private LocalDateTime fechaRecepcion;
    private LocalDateTime fechaEntrega;

    public ServicioEntrega(Pedido pedido, Direccion direccion, Repartidor repartidor) {
        this.pedido = pedido;
        this.direccion = direccion;
        this.repartidor = repartidor;
        this.fechaRecepcion = null; // Inicialmente no se ha registrado la recepción
        this.fechaEntrega = null;   // Inicialmente no se ha registrado la entrega
    }

	public Pedido getPedido() {
        return pedido;
    }
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    public Direccion getDireccion() {
        return direccion;
    }
    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }
    public Repartidor getRepartidor() {
        return repartidor;
    }
    public void setRepartidor(Repartidor repartidor) {
        this.repartidor = repartidor;
    }
    public LocalDateTime getFechaRecepcion() {
        return fechaRecepcion;
    }
    public LocalDateTime getFechaEntrega() {
        return fechaEntrega;
    }
    
    //otros metodos
    public void registrarRecepcion() {
        this.fechaRecepcion = LocalDateTime.now();
    }
    public void registrarEntrega() {
        this.fechaEntrega = LocalDateTime.now();
    }
    public boolean entregaCompletada() {
        return fechaEntrega != null;
    }
   
}