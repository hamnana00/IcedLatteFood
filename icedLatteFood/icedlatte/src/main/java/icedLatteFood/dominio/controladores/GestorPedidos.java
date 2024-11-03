package icedLatteFood.dominio.controladores;

import icedLatteFood.dominio.entidades.*;
import icedLatteFood.persistencia.PedidoDAO;
import icedLatteFood.persistencia.ServicioEntregaDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class GestorPedidos {

    private PedidoDAO pedidoDAO;
    private ServicioEntregaDAO servicioEntregaDAO;
    private Pedido pedidoEnMarcha;

    public GestorPedidos(icedLatteFood.persistencia.GestorBaseDatos gestorBD) {
        this.pedidoDAO = new PedidoDAO(gestorBD);
        this.servicioEntregaDAO = new ServicioEntregaDAO(gestorBD);
    }

    // Agregar el método de pago como parámetro
    public void realizarPedido(Cliente cliente, Restaurante restaurante, List<ItemMenu> items, MetodoPago metodoPago) {
        // Crear un nuevo pedido
        Pedido nuevoPedido = new Pedido(0, cliente.getId(), cliente.getNombre(), restaurante.getNombre(),
                0, 0.0, new Date(), 0); // Asigna valores válidos para la creación

        // Establecer el cliente y el restaurante del pedido
        nuevoPedido.setCliente(cliente);
        nuevoPedido.setRestaurante(restaurante);

        // Agregar los items al pedido
        for (ItemMenu item : items) {
            nuevoPedido.addItem(item); // Agregar los items al pedido
        }

        // Calcular el precio total del pedido
        nuevoPedido.setPrecioTotal(nuevoPedido.getPrecioTotal());

        // Insertar el pedido en la base de datos
        int pedidoId = pedidoDAO.insert(nuevoPedido);
        if (pedidoId > 0) {
            nuevoPedido.setId(pedidoId); // Asignar el ID generado
            this.pedidoEnMarcha = nuevoPedido; // Guardar el pedido en marcha

            // Realizar el pago
            if (!realizarPago(nuevoPedido, metodoPago)) {
                System.out.println("Error al procesar el pago.");
            }
        } else {
            System.out.println("Error al realizar el pedido.");
        }
    }

    private boolean realizarPago(Pedido pedido, MetodoPago metodoPago) {
        // Crear el objeto Pago con el método de pago seleccionado
        Pago pago = new Pago(pedido, metodoPago);
        boolean resultado = pago.procesarPago(); // Procesar el pago

        if (resultado) {
            // Actualizar el estado del pedido en la base de datos
            pedidoDAO.update(pedido); // Actualiza el estado a PAGADO
            return true; // Pago exitoso
        }
        return false; // Pago fallido
    }

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

    public void comenzarPedido(ResultSet rs, Restaurante restaurante) {
        try {
            if (rs.next()) { // Verifica si hay al menos un registro en el ResultSet
                pedidoEnMarcha = new Pedido(
                        rs.getInt("idPed"),         // ID del pedido
                        rs.getInt("idCli"),         // ID del cliente
                        rs.getString("nombre"),      // Nombre del cliente
                        rs.getString("origen"),      // Origen del pedido
                        rs.getInt("destino"),        // Destino del pedido
                        rs.getDouble("precioTotal"), // Precio total del pedido
                        new Date(),                  // Fecha actual o la fecha del ResultSet si corresponde
                        rs.getInt("idRepar")        // ID del repartidor
                ); // Inicializa un nuevo pedido

                pedidoEnMarcha.setRestaurante(restaurante); // Asigna el restaurante al pedido
            } else {
                System.out.println("No hay datos en el ResultSet.");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Maneja cualquier excepción que pueda ocurrir
        }
    }
}
