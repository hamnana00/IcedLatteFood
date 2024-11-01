package dominio.controladores;

import persistencia.PedidoDAO;
import persistencia.ServicioEntregaDAO;
import dominio.entidades.*;
import java.util.List;

public class GestorPedidos {
    private PedidoDAO pedidoDAO;
    private ServicioEntregaDAO servicioEntregaDAO;
    private Pedido pedidoEnMarcha;

    public GestorPedidos(GestorBaseDatos gestorBD) {
        this.pedidoDAO = new PedidoDAO(gestorBD);
        this.servicioEntregaDAO = new ServicioEntregaDAO(gestorBD);
    }

    public void realizarPedido(Cliente cliente, Restaurante restaurante, List<ItemMenu> items) {
        Pedido nuevoPedido = new Pedido(new java.util.Date());
        nuevoPedido.setCliente(cliente);
        nuevoPedido.setRestaurante(restaurante);
        items.forEach(nuevoPedido::addItem);

        // Insertar el pedido en la base de datos y obtener el ID generado
        int pedidoId = pedidoDAO.insert(nuevoPedido);
        if (pedidoId > 0) {
            nuevoPedido.setId(pedidoId);
            this.pedidoEnMarcha = nuevoPedido;
            System.out.println("Pedido realizado con ID: " + pedidoId);
        } else {
            System.out.println("Error al realizar el pedido.");
        }
    }

    public boolean realizarPago() {
        if (pedidoEnMarcha != null) {
            Pago pago = new Pago(pedidoEnMarcha, MetodoPago.TARJETA);
            boolean resultadoPago = pago.procesarPago();

            if (resultadoPago) {
                pedidoEnMarcha.setEstado(EstadoPedido.PAGADO);
                pedidoEnMarcha.setPago(pago);
                pedidoDAO.update(pedidoEnMarcha);
                System.out.println("Pago procesado con éxito para el pedido con ID: " + pedidoEnMarcha.getId());
                return true;
            } else {
                System.out.println("El pago ha fallado.");
                return false;
            }
        }
        System.out.println("No hay un pedido en marcha para procesar el pago.");
        return false;
    }

    public void anadirItemMenu(ItemMenu item) {
        if (pedidoEnMarcha != null) {
            pedidoEnMarcha.addItem(item);
            System.out.println("Item añadido al pedido en marcha.");
        } else {
            System.out.println("No hay un pedido en marcha.");
        }
    }

    public void eliminarItemMenu(ItemMenu item) {
        if (pedidoEnMarcha != null) {
            pedidoEnMarcha.deleteItem(item);
            System.out.println("Item eliminado del pedido en marcha.");
        } else {
            System.out.println("No hay un pedido en marcha.");
        }
    }
}
