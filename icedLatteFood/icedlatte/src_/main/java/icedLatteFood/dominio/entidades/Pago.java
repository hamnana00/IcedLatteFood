package dominio.entidades;
import java.util.UUID;
import java.util.Date;

public class Pago {

    Pedido pedido;
    MetodoPago tipo;
    private UUID idTransaccion;
    private Date fechaTransaccion;

    public Pago(Pedido pedido, MetodoPago tipo) {
        this.pedido = pedido;
        this.tipo = tipo;
    }

    public UUID getIdTransaccion() {
        return idTransaccion;
    }
    public Date getFechaTransaccion() {
        return fechaTransaccion;
    }

    public boolean procesarPago() {
        if (tipo == null) {
            System.out.println("Metodo de pago no seleccionado");
            return false;
        }

        //llamar a api
        this.idTransaccion = UUID.randomUUID(); // Generr id para transaccion
        this.fechaTransaccion = new Date(); // Registra la fecha y hora
        pedido.setEstado(Pedido.Estado.PAGADO); // Actualiza el estado del pedido a PAGADO

        // Imprimir o registrar detalles del pago
        System.out.println("Pago procesado con éxito. ID de transacción: " + idTransaccion);
        System.out.println("Fecha de transacción: " + fechaTransaccion);

        return true; // Devuelve true si el pago fue exitoso
    }
}