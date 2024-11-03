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
     * @param item El item de menú que se añadirá al pedido.
     */
    public void anadirItemMenu(ItemMenu item) {
        Pedido pedidoEnMarcha = gestorPedidos.getPedidoEnMarcha();
        if (pedidoEnMarcha != null) {
            pedidoEnMarcha.addItem(item); // Agrega el item al pedido en marcha
            gestorPedidos.anadirItemMenu(item); // Actualiza en la capa lógica
        } else {
            System.out.println("No hay un pedido en marcha para agregar un item.");
        }
    }

    /**
     * Elimina un item del pedido en marcha.
     *
     * @param item El item de menú que se eliminará del pedido.
     */
    public void eliminarItemMenu(ItemMenu item) {
        Pedido pedidoEnMarcha = gestorPedidos.getPedidoEnMarcha();
        if (pedidoEnMarcha != null) {
            pedidoEnMarcha.removeItem(item); // Elimina el item del pedido en marcha
            gestorPedidos.eliminarItemMenu(item); // Actualiza en la capa lógica
        } else {
            System.out.println("No hay un pedido en marcha para eliminar un item.");
        }
    }

    /**
     * Comienza un nuevo pedido para el restaurante especificado.
     *
     * @param nombreRestaurante El nombre del restaurante.
     * @param cif El CIF (Código de Identificación Fiscal) del restaurante.
     * @param favorito Indica si el restaurante es favorito.
     * @param direccion La dirección del restaurante.
     * @return Un nuevo objeto Pedido en marcha.
     */
    public Pedido comenzarPedido(String nombreRestaurante, String cif, boolean favorito, Direccion direccion) {
        // Crea el objeto Restaurante con el constructor actualizado
        Restaurante restaurante = new Restaurante(nombreRestaurante, cif, favorito, direccion);

        // Llama a la capa de lógica para comenzar el pedido
        gestorPedidos.comenzarPedido(null, restaurante);

        // Retorna el pedido en curso
        return gestorPedidos.getPedidoEnMarcha();
    }



    /**
     * Finaliza el pedido y procesa los elementos necesarios, como el pago.
     *
     * @return true si el pedido finaliza con éxito, false en caso contrario.
     */
    public boolean finalizarPedido(MetodoPago metodoPago) {
        Pedido pedidoEnMarcha = gestorPedidos.getPedidoEnMarcha(); // Obtiene el pedido en marcha
        if (pedidoEnMarcha != null && realizarPago(pedidoEnMarcha, metodoPago)) { // Realiza el pago
            pedidoEnMarcha.setEstado(EstadoPedido.PAGADO); // Marca el pedido como pagado
            gestorPedidos.finalizarPedido(pedidoEnMarcha); // Finaliza el pedido
            generarServicioEntrega(pedidoEnMarcha); // Genera el servicio de entrega
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
    // Método para realizar el pago del pedido
    private boolean realizarPago(Pedido pedido, MetodoPago metodoPago) {
        return gestorPedidos.realizarPago(pedido, metodoPago); // Llama a la capa lógica para procesar el pago
    }

    /**
     * Genera un servicio de entrega para el pedido especificado.
     *
     * @param pedido El pedido para el cual se generará el servicio de entrega.
     */
    private void generarServicioEntrega(Pedido pedido) {
        if (pedido != null) {
            Direccion direccionCliente = pedido.getCliente().getDireccion();
            gestorPedidos.crearServicioEntrega(pedido, direccionCliente);
        } else {
            System.out.println("No se pudo generar el servicio de entrega porque el pedido es nulo.");
        }
    }
}
