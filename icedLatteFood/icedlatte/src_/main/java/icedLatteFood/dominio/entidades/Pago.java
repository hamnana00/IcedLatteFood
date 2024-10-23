package dominio.entidades;

import java.util.Date;
import java.util.UUID;

public class Pago {
    private Pedido pedido;
    private MetodoPago tipo;
    private UUID idTransaccion;
    private Date fechaTransaccion;

    // Constructor
    public Pago(UUID idTransaccion, Date fechaTransaccion) {
        this.idTransaccion = idTransaccion;
        this.fechaTransaccion = fechaTransaccion;
    }

    // Getters y Setters
    public UUID getIdTransaccion() { return idTransaccion; }
    public void setIdTransaccion(UUID idTransaccion) { this.idTransaccion = idTransaccion; }

    public Date getFechaTransaccion() { return fechaTransaccion; }
    public void setFechaTransaccion(Date fechaTransaccion) { this.fechaTransaccion = fechaTransaccion; }
}
