package icedLatteFood.dominio.entidades;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ServicioEntrega {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEntrega;
    @Column
    Pedido pedido;
    @Column
    Direccion direccion;
    @Column
    Repartidor repartidor;
    @Column
    private LocalDateTime fechaRecepcion;
    @Column
    private LocalDateTime fechaEntrega;
/*
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
        return direccion; // Cambié a devolver el objeto Direccion
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

    // Otros métodos
    public void registrarRecepcion() {
        this.fechaRecepcion = LocalDateTime.now();
    }

    public void registrarEntrega() {
        this.fechaEntrega = LocalDateTime.now();
    }

    public boolean entregaCompletada() {
        return fechaEntrega != null;
    }

    // Método para obtener el ID del repartidor
    public int getRepartidorId() {
        if (repartidor != null) { // Verifica que el repartidor no sea nulo
            return repartidor.getId(); // Retorna el ID del repartidor
        } else {
            throw new IllegalStateException("No hay repartidor asociado al servicio de entrega."); // Manejo de error si no hay repartidor
        }
    }*/
}
