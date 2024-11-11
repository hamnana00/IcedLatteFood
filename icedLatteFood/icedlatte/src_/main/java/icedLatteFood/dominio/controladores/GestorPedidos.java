package dominio.controladores;

import persistencia.*;
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
        // Crear un nuevo pedido
        Pedido nuevoPedido = new Pedido(new java.util.Date()); // Inicializa con la fecha actual
        nuevoPedido.setCliente(cliente);
        nuevoPedido.setRestaurante(restaurante);
        for (ItemMenu item : items) {
            nuevoPedido.addItem(item); // Agregar los items al pedido
        }

        // Insertar el pedido en la base de datos
        int pedidoId = pedidoDAO.insert(nuevoPedido);
        if (pedidoId > 0) {
            nuevoPedido.setId(pedidoId); // Asignar el ID generado
            this.pedidoEnMarcha = nuevoPedido; // Guardar el pedido en marcha
        } else {
            System.out.println("Error al realizar el pedido.");
        }
    }

    private boolean realizarPago(Pedido pedido) {
        // Asumimos que tenemos una clase Pago y un método para procesar el pago
        Pago pago = new Pago(pedido, MetodoPago.TARJETA); // Suponiendo que el método de pago es tarjeta
        boolean resultado = pago.procesarPago();
        if (resultado) {
            // Actualizar el estado del pedido en la base de datos
            pedidoDAO.update(pedido); // Actualiza el estado a PAGADO
            return true;
        }
        return false; // Pago fallido
    }

   /* private ServicioEntrega crearServicioEntrega(Pedido pedido, Direccion direccion) {
        Repartidor repartidor = ServicioEntrega;
        servicio = new ServicioEntrega(pedido, direccion, repartidor);
        // Lógica para seleccionar un repartidor, puede ser aleatorio o basado en condiciones
        servicio.registrarRecepcion(); // Registrar la recepción del servicio de entrega
        return servicio;
    }*/

    public void anadirItemMenu(ItemMenu item) {
        if (pedidoEnMarcha != null) {
            pedidoEnMarcha.addItem(item); // Añadir item al pedido en marcha
        } else {
            System.out.println("No hay un pedido en marcha.");
        }
    }

    public void eliminarItemMenu(ItemMenu item) {
        if (pedidoEnMarcha != null) {
            pedidoEnMarcha.removeItem(item); // Eliminar item del pedido en marcha
        } else {
            System.out.println("No hay un pedido en marcha.");
        }
    }

    public void comenzarPedido(Restaurante restaurante) {
        pedidoEnMarcha = new Pedido(new java.util.Date()); // Iniciar un nuevo pedido
        pedidoEnMarcha.setRestaurante(restaurante);
    }
}