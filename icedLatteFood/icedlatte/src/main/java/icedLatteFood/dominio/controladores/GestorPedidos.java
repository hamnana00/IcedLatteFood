package icedLatteFood.dominio.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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

    // Método para realizar un pedido
    public void realizarPedido(Cliente cliente, Restaurante restaurante, List<ItemMenu> items, MetodoPago metodoPago) {
        Pedido nuevoPedido = new Pedido(0, cliente.getIdUsuario(), cliente.getNombre(), restaurante.getNombre(), 0, 0.0, new Date(), 0);
        nuevoPedido.setCliente(cliente);
        nuevoPedido.setRestaurante(restaurante);

        for (ItemMenu item : items) {
            nuevoPedido.addItem(item);
        }

        nuevoPedido.setPrecioTotal(nuevoPedido.getPrecioTotal()); // Esto ahora debe calcular correctamente el total

        // Usar el DAO para insertar el nuevo pedido
        int pedidoId = pedidoDAO.insert(nuevoPedido);
        if (pedidoId > 0) {
            nuevoPedido.setId(pedidoId); // Asigna el ID del pedido después de la inserción
            this.pedidoEnMarcha = nuevoPedido;

            if (!realizarPago(nuevoPedido, metodoPago)) {
                System.out.println("Error al procesar el pago.");
            }
        } else {
            System.out.println("Error al realizar el pedido.");
        }
    }

    public boolean realizarPago(Pedido pedido, MetodoPago metodoPago) {
        Pago pago = new Pago(pedido, metodoPago);
        boolean resultado = pago.procesarPago();

        if (resultado) {
            pedido.setEstado(EstadoPedido.PAGADO);
            pedidoDAO.update(pedido);
            return true;
        }
        return false;
    }

    public void anadirItemMenu(ItemMenu item) {
        if (pedidoEnMarcha != null) {
            pedidoEnMarcha.addItem(item);
        } else {
            System.out.println("No hay un pedido en marcha.");
        }
    }

    public void eliminarItemMenu(ItemMenu item) {
        if (pedidoEnMarcha != null) {
            pedidoEnMarcha.removeItem(item);
        } else {
            System.out.println("No hay un pedido en marcha.");
        }
    }

    public void comenzarPedido(ResultSet rs, Restaurante restaurante) {
        try {
            if (rs.next()) {
                pedidoEnMarcha = new Pedido(
                        rs.getInt("idPed"),
                        rs.getString("idCli"),
                        rs.getString("nombre"),
                        rs.getString("origen"),
                        rs.getInt("destino"),
                        rs.getDouble("precioTotal"),
                        new Date(),
                        rs.getInt("idRepar")
                );

                pedidoEnMarcha.setRestaurante(restaurante);
            } else {
                System.out.println("No hay datos en el ResultSet.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para finalizar un pedido
    public void finalizarPedido(Pedido pedido) {
        if (pedido.getEstado() == EstadoPedido.PAGADO) {
            // Crear el servicio de entrega
            Direccion direccionCliente = pedido.getCliente().getDireccion();
            Repartidor repartidor = asignarRepartidor(); // Método para asignar un repartidor
            ServicioEntrega servicioEntrega = new ServicioEntrega(pedido, direccionCliente, repartidor);

            // Registrar el servicio de entrega en la base de datos
            int success = servicioEntregaDAO.insert(servicioEntrega); // Cambiado a boolean
            if (success > 0)  {
                pedido.setEntrega(servicioEntrega);
                pedido.setEstado(EstadoPedido.RECOGIDO); // Cambiar el estado a RECOGIDO

                // Actualizar el pedido en la base de datos
                pedidoDAO.update(pedido);
                System.out.println("El pedido ha sido finalizado y está listo para ser recogido.");
            } else {
                System.out.println("Error al registrar el servicio de entrega.");
            }
        } else {
            System.out.println("El pedido no puede finalizarse porque no está pagado.");
        }
    }


    private Repartidor asignarRepartidor() {
        // Proporcionar valores de ejemplo para todos los parámetros del constructor
        String nombre = "Nombre Repartidor";
        String apellidos = "Apellidos Repartidor";
        String nif = "12345678A"; // Asumiendo un NIF ficticio
        int eficiencia = 85; // Por ejemplo, 85% de eficiencia

        // Crear el repartidor con los parámetros correctos
        return new Repartidor(nombre, apellidos, nif, eficiencia);
    }


    public Pedido getPedidoEnMarcha() {
        return pedidoEnMarcha;
    }

    public void crearServicioEntrega(Pedido pedido, Direccion direccionCliente) {
        Repartidor repartidor = asignarRepartidor(); // Asignar un repartidor
        ServicioEntrega servicioEntrega = new ServicioEntrega(pedido, direccionCliente, repartidor);

        // Registrar el servicio de entrega en la base de datos
        int success = servicioEntregaDAO.insert(servicioEntrega);
        if (success > 0)  {
            pedido.setEntrega(servicioEntrega); // Solo asigna si se insertó correctamente
            System.out.println("El servicio de entrega se ha registrado correctamente.");
        } else {
            System.out.println("Error al registrar el servicio de entrega.");
        }
    }



}

