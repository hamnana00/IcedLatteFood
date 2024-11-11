package icedLatteFood.dominio.entidades;

import java.util.UUID;
import java.util.Date;
import icedLatteFood.dominio.entidades.EstadoPedido;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPago;
    @Column
    icedLatteFood.dominio.entidades.Pedido pedido;
    @Column
    MetodoPago tipo;
    @Column
    private UUID idTransaccion;
    @Column
    private Date fechaTransaccion;

    public Pago(icedLatteFood.dominio.entidades.Pedido pedido, MetodoPago tipo) {
        this.idPago=idPago;
        this.pedido = pedido;
        this.tipo = tipo;
    }
    public Long getIdPago() {
        return idPago;
    }

    public void setIdPago(Long idPago) {
        this.idPago = idPago;
    }

    public UUID getIdTransaccion() {
        return idTransaccion;
    }
    public Date getFechaTransaccion() {
        return fechaTransaccion;
    }

    public boolean procesarPago() {
        if (tipo == null) {
            System.out.println("Método de pago no seleccionado");
            return false;
        }

        // Llamar a API
        this.idTransaccion = UUID.randomUUID(); // Generar ID para transacción
        this.fechaTransaccion = new Date(); // Registra la fecha y hora
        pedido.setEstado(EstadoPedido.PAGADO); // Actualiza el estado del pedido a PAGADO

        // Imprimir o registrar detalles del pago
        System.out.println("Pago procesado con éxito. ID de transacción: " + idTransaccion);
        System.out.println("Fecha de transacción: " + fechaTransaccion);

        return true; // Devuelve true si el pago fue exitoso
    }
}
