package icedLatteFood.presentacion;

import icedLatteFood.dominio.entidades.*;
import icedLatteFood.dominio.controladores.GestorPedidos;
import java.util.List;

public class IUPedido {

    private final GestorPedidos gestorPedidos;

    // Constructor que recibe el gestor de pedidos
    public IUPedido(GestorPedidos gestorPedidos) {
        this.gestorPedidos = gestorPedidos;
    }

    /**
     * Añade un item al pedido en marcha.
     *
     * @param pedido El pedido actual al cual se añadirá el item.
     * @param item El item de menú que se añadirá al pedido.
     */
    public void anadirItemMenu(Pedido pedido, ItemMenu item) {
        pedido.addItem(item); // Agrega el item al pedido
        gestorPedidos.anadirItemMenu(item); // Actualiza en la capa lógica
    }

    /**
     * Elimina un item del pedido en marcha.
     *
     * @param pedido El pedido actual del cual se eliminará el item.
     * @param item El item de menú que se eliminará del pedido.
     */
    public void eliminarItemMenu(Pedido pedido, ItemMenu item) {
        pedido.deleteItem(item); // Elimina el item del pedido
        gestorPedidos.eliminarItemMenu(item); // Actualiza en la capa lógica
    }

    /**
     * Comienza un nuevo pedido para el restaurante especificado.
     *
     * @param idRestaurante El ID del restaurante para el cual se inicia el pedido.
     * @return Un nuevo objeto Pedido en marcha.
     */
    public Pedido comenzarPedido(String idRestaurante) {
        Restaurante restaurante = new Restaurante(idRestaurante, "Nombre del Restaurante"); // Esto es un ejemplo; ajusta con datos reales.
        gestorPedidos.comenzarPedido(restaurante);
        return gestorPedidos.getPedidoEnMarcha(); // Retorna el pedido en curso
    }

    /**
     * Finaliza el pedido y procesa los elementos necesarios, como el pago.
     *
     * @param pedido El pedido que se desea finalizar.
     * @return true si el pedido finaliza con éxito, false en caso contrario.
     */
    public boolean finalizarPedido(Pedido pedido) {
        if (realizarPago(pedido)) {
            pedido.setEstado(EstadoPedido.PAGADO); // Marca el pedido como pagado
            gestorPedidos.finalizarPedido(pedido); // Actualiza en la base de datos
            generarServicioEntrega(pedido); // Genera el servicio de entrega
            return true;
        }
        return false; // No se pudo finalizar
    }

    /**
     * Realiza el pago del pedido.
     *
     * @param pedido El pedido para el cual se realiza el pago.
     * @return true si el pago fue exitoso, false en caso contrario.
     */
    private boolean realizarPago(Pedido pedido) {
        return gestorPedidos.realizarPago(pedido); // Llama a la capa lógica para procesar el pago
    }

    /**
     * Genera un servicio de entrega para el pedido especificado.
     *
     * @param pedido El pedido para el cual se generará el servicio de entrega.
     * @return El servicio de entrega generado.
     */
    private ServicioEntrega generarServicioEntrega(Pedido pedido) {
        Direccion direccionCliente = pedido.getCliente().getDireccion();
        ServicioEntrega servicioEntrega = gestorPedidos.crearServicioEntrega(pedido, direccionCliente);
        return servicioEntrega;
    }
}
